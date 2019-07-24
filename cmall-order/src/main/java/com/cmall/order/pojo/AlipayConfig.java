package com.cmall.order.pojo;

public class AlipayConfig {
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
    public static String return_url = "http://47.100.242.105/#/center/trade";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://47.100.242.105";
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091900547266";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTf9R6SZfPf14FJuPMU+8mJS7IRy4RjuY1U31dZ6PswYa9TlrQJbeoCqm9HnBPkNXoC5EX7m8i1uM+b0UGvhS7z6xZYlhwH7DXD56iirGKyw0zrikL+XapsA/LxuNDLMv5H+m8VJtq8mC6U2PnSjbXPjWplhalim48K+1QVSw4Q8h3pnkzreys31pT4MIldMC/NimQ38MgQ7PlNVxwQ5rYrKoyyN28fSyUcRH36uEaFnbC1evxCNvGJkgR9U3i2K2EVlFe6AWOesDpd0Vbl7deVVJ1+efgnqUk2Mci/NS4E1CUGI3uNLIe4W/YxJSQ2lzkbcE+DsGJXRu8oK7IdILQIDAQAB";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSFVrhb8EAx3pkIxso2ztI2GW+VVbMp031P6nrGvZ2+uu6EmDD3rkV87OTIKhxKRDC/Bv6SmUvH6/9s5dycxqRpDCcunPLUKh7I+yElVlYS7tlzwzEYPRjvECzqa24Mue8He1XDFGBR3aYlGuXAiMg3zdIbOaP/pdzdxBvRaOwodyXMvmx6ZGJop593siKr7v6Nnxyr2HAV3Y0dLBhNuwDbXOLTc9jsPq2OAfJkWwwIVA83GNY2JnC5DStb/cLjBgOGc2dKxg30YQJzYyhlq9eQJosuFp/soZKnWyNgfNbJewd1UFk8YDaJFAu/k7b7YfJfuWzBA6NvJdjb/ASQ0XnAgMBAAECggEAZenCCk5lrn5700pukzVHCc32RS/SK1p416T/jQa1dVIejCRwH5XNOBRZbkY1XSTSDcXGWvn110dbBzp4aJ8GpR6Pz3eAexIdSDNKuUBcIW5MIX2uIX6mYMIfvXedu0jARIv4XWc5oWBSy6WX3bTe/vra3Ysj2sc5r5McAxTpWrqsR0n6Z8xTn7a70qQ8dfg6d6A8O4bLITe1df0KG8Nnq+t0Wz3cFl/uN52T2Mz2BnPFrNSyh+iaJZf4YIBm3EeTsFvu5sLR3+rP8vMnRWQqqLB7U28bFk10h1Qsl/YXmRF6iAJJ03vdiexPgASCN6tx0QjEC/IQpeY2PMjaszJj6QKBgQDz1SkpnAMKd/37d3bITbQyCu++5jTD+LhRTG/mafIWJXb2XLBhLR/XvkYNP3cU3ETeD/Mhs7A7enugucDVSQLuTpd/trj7yzA0WiglW6C8DuMZg+q8BfG//0Trnvk69oXITNG1yeZKzAshxXRFjJ9aLPf63P3NljeVWZ8Kw68n5QKBgQCZX385RHE+sCjQR/SbXi0d+N6QG25yFW8wYuaroAOXY7zZeZvwdbycY6WE8YPi+ACMfPoxITk4tKayBE8urdJtscgsy0CJt1YK0rrgAVtvbBUnz+Zb4G7tP77yVjXPZMJud7Jf/rMegZDBRXD2QTmwJHMLnu75hBZzkTna7m9B2wKBgDgEMICupsQw7NhoiZFayJr2iD2DBXxAF5gVx19X15Xe5YWTpbGtfDNEMzO38NOKBwg9Ac4sScBgUyaJhhN5woKc65EbmUO/WkOssqxicHrDsMib1cF8XB2Pt3Wp//fbysBJZaQtzgpA9g28HwobVECTNCUdv/ELIZneDPucGRFBAoGAA9VVMrM5OV3hx0LlJDT4X7Vmqqdk3UkX3Fe8eDGppVEC2VSmxx3KNwZujqISr8uPB/PftPalnXs2Z2CgMgR6K++zlxsFY+vHJMIblZmzFVfYN4SEt2smmptrRFdPRP25tlUbijatd2e5nHsF1cHPlU1z6qhwRKA+h2jrz0MCWN0CgYEA6QU02ARr/bBF8jA1Xgp7FrG5iTTDTJxA7RC1wWvbRTs9xSj1VOw31VZ5PZ7Favu8AB+GPU9E4q6ff45UJYeiTk8wC4VQcom3POezTvcK6lffbm05Mv9nYXkhoaxVAGjOILCZYGx8m8hHeEwjJkhu8Ks+5QzpX4g9yltFaljWN5g=";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    //日志
    public static String Log_path = "E:\\";
}
