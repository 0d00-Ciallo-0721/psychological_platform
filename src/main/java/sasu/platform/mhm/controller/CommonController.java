package sasu.platform.mhm.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.service.CommonService;
import sasu.platform.mhm.util.OssUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private OssUtil ossUtil;
    @GetMapping("/getVerificationCode")
    public void getVerificationCode(HttpServletResponse response){
        log.info("发送验证码");
        Map<String,byte[]> verificationCode = commonService.verificationCode();
        Set<String> keySet = verificationCode.keySet();
        String key = keySet.iterator().next();
        byte[] bytes = verificationCode.get(key);
        response.setContentType("image/png");
        response.setHeader("key",key);
        try {
            OutputStream outputStream = response.getOutputStream();
            //将图片字节写入响应流
            outputStream.write(bytes);
            //刷新响应流
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("验证码输出失败");
        }

    }
    @PostMapping("/login")
    public R login(@RequestParam("username") String username,
                   @RequestParam("password") String password,
                   @RequestParam("verificationCode") String verificationCode,
                   @RequestHeader("key") String key){
        log.info("登录{}", username);
        Map<String, String> map = commonService.login(username,password,verificationCode,key);
        return R.success(map);
    }

    /**
     * 检查服务器状态是否健康
     */
    @GetMapping("/health")
    public R checkHealth(){
        log.info("检查服务器状态");
        return R.success("服务器正常");
    }

    /**
     * 通用文件上传
     */
    @PostMapping("/upload")
    public R<Map<String, String>> upload(@RequestParam("file") MultipartFile file,
                                          @RequestParam(value = "folder", required = false, defaultValue = "common") String folder) {
        log.info("上传文件: folder={}, filename={}", folder, file.getOriginalFilename());
        try {
            String url = ossUtil.uploadFile(file, folder);
            return R.success(Map.of("url", url));
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return R.error("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 图片上传
     */
    @PostMapping("/upload/image")
    public R<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        log.info("上传图片: filename={}", file.getOriginalFilename());
        try {
            // 验证文件类型
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null) {
                String ext = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!ext.matches("\\.(jpg|jpeg|png|gif|webp|bmp)")) {
                    return R.error("不支持的文件类型，仅支持图片格式");
                }
            }
            String url = ossUtil.uploadFile(file, "image");
            return R.success(Map.of("url", url));
        } catch (Exception e) {
            log.error("图片上传失败", e);
            return R.error("图片上传失败: " + e.getMessage());
        }
    }
}
