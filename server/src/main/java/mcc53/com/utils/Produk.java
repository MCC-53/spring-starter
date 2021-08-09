package mcc53.com.utils;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produk {

    private String name;
    private String category;
    private Integer harga;
    private Integer diskon;
    private String warna;
    private String desc;
    private String ukuran;
    private String dimensi;
    private Integer stok;
    private Integer berat;
    private String kondisi;
}
