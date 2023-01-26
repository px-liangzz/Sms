package net.abjy.sms.shiro;

import net.abjy.sms.entity.RolesPermissions;
import net.abjy.sms.service.RolesPermissionsService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;

import java.util.*;

public class ShiroPermissionFactory extends ShiroFilterFactoryBean {
    /**记录配置中的过滤链*/
    public static String definition = "";
    private RolesPermissionsService rolesPermissionsService;

    public ShiroPermissionFactory(RolesPermissionsService rolesPermissionsService){
        this.rolesPermissionsService = rolesPermissionsService;
    }

    /**
     * 初始化设置过滤链
     */
    @Override
    public void setFilterChainDefinitions(String definitions) {

        definition = definitions;//记录配置的静态过滤链

        List<RolesPermissions> rolesPermissions = rolesPermissionsService.selectList();
        Set<String> urls = new LinkedHashSet<>();
        for (RolesPermissions rolesPermission : rolesPermissions) {
            urls.add(rolesPermission.getUrl());
        }

        Map<String,String> otherChains = new HashMap<>();
        for (String url : urls) {
            StringBuilder roleOrFilters = new StringBuilder();
            int j = 0;
            for (int i = 0; i < rolesPermissions.size(); i++) {
                if (Objects.equals(url, rolesPermissions.get(i).getUrl())) {
                    if (j == 0) {
                        j = 1;
                        roleOrFilters.append(rolesPermissions.get(i).getRolesName());
                    } else {
                        roleOrFilters.append(",").append(rolesPermissions.get(i).getRolesName());
                    }
                }
            }
            String rolesStr = roleOrFilters.toString();
            if (!"".equals(rolesStr)) {
                otherChains.put(url, "roleOrFilter[" + rolesStr + "]");
            }
        }
        //加载配置默认的过滤链
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        //加上数据库中过滤链
        section.putAll(otherChains);
        setFilterChainDefinitionMap(section);
    }
}