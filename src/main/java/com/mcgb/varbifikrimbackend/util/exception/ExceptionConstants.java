package com.mcgb.varbifikrimbackend.util.exception;

public interface ExceptionConstants {
    String GIRIS_BILGILERI_HATALI = "Giriş bilgileri hatalı.";
    String KULLANICI_BULUNAMADI = "%s kullanıcısı bulunamadı.";
    String KULLANICI_AKTIF_DEGIL = "Kullanıcınızın durumu aktif değil. Üyeliğiniz sona ermiş veya hesabınız belirli bir sebepten dolayı kapatılmış olabilir. Lütfen sistem yöneticisi ile iletişime geçiniz.";
    String KULLANICI_ADI_ALINMIS = "%s kullanıcı adı daha önce alınmış.";
    String DOGRULAMA_KODU_HATALI = "Hatalı doğrulama kodu. Lütfen sistem yöneticisi ile iletişimi geçiniz.";
}
