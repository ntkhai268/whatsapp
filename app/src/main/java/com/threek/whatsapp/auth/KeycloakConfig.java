package com.threek.whatsapp.auth;

/**
 * Keycloak server configuration.
 * Update these values to match your Keycloak deployment.
 */
public class KeycloakConfig {

    // =====================================================
    // ⚠️ CẬP NHẬT CÁC GIÁ TRỊ NÀY THEO SERVER CỦA BẠN
    // =====================================================

    /** Keycloak server base URL (ví dụ: http://10.0.2.2:8080 cho emulator) */
    public static final String SERVER_URL = "http://10.0.2.2:8080";

    /** Realm name trong Keycloak */
    public static final String REALM = "whatsapp";

    /** Client ID đã đăng ký trong Keycloak */
    public static final String CLIENT_ID = "whatsapp-mobile";

    /** Redirect URI sau khi đăng nhập — phải khớp với Keycloak client config */
    public static final String REDIRECT_URI = "com.threek.whatsapp://oauth2redirect";

    // =====================================================
    // Các URL tự động tạo từ config trên (không cần sửa)
    // =====================================================

    /** OpenID Connect Discovery URL */
    public static String getDiscoveryUrl() {
        return SERVER_URL + "/realms/" + REALM + "/.well-known/openid-configuration";
    }

    /** Authorization endpoint */
    public static String getAuthorizationEndpoint() {
        return SERVER_URL + "/realms/" + REALM + "/protocol/openid-connect/auth";
    }

    /** Token endpoint */
    public static String getTokenEndpoint() {
        return SERVER_URL + "/realms/" + REALM + "/protocol/openid-connect/token";
    }

    /** End session (logout) endpoint */
    public static String getEndSessionEndpoint() {
        return SERVER_URL + "/realms/" + REALM + "/protocol/openid-connect/logout";
    }

    /** UserInfo endpoint */
    public static String getUserInfoEndpoint() {
        return SERVER_URL + "/realms/" + REALM + "/protocol/openid-connect/userinfo";
    }
}
