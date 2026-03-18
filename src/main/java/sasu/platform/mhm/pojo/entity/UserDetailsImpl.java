package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor    // 这两个注解可以帮我们自动生成 get、set、无参构造函数
public class UserDetailsImpl implements UserDetails {

    private User user;	// 通过有参构造函数填充赋值的
    
    /**
     * 使用User对象构造UserDetailsImpl
     * @param user 用户对象
     */
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 从用户角色中获取权限
        if (user == null) {
            return Collections.emptyList();
        }
        // 使用 getter 方法访问字段
        String roleCode = user.getRole_code();
        if (roleCode == null || roleCode.isEmpty()) {
            return Collections.emptyList();
        }
        // 将role_code转换为权限，格式为ROLE_XXX
        if (!roleCode.startsWith("ROLE_")) {
            roleCode = "ROLE_" + roleCode;
        }
        return List.of(new SimpleGrantedAuthority(roleCode));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {  // 检查账户是否 没过期。
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   // 检查账户是否 没有被锁定。
        // 根据accountStatus判断：0-禁用, 1-正常, 2-锁定
        return user != null && user.getAccountStatus() != null && user.getAccountStatus() != 2;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //检查凭据（密码）是否 没过期。
        return true;
    }

    @Override
    public boolean isEnabled() {    // 检查账户是否启用。
        // 根据accountStatus判断：0-禁用, 1-正常, 2-锁定
        return user != null && user.getAccountStatus() != null && user.getAccountStatus() == 1;
    }

    // 这个方法是 @Data注解 会自动帮我们生成，用来获取 loadUserByUsername 中最后我们返回的创建UserDetailsImpl对象时传入的User。
    // 如果你的字段包含 username和password 的话可以用强制类型转换, 把 UserDetailsImpl 转换成 User。如果不能强制类型转换的话就需要用到这个方法了
    public User getUser() {
        return user;
    }
}