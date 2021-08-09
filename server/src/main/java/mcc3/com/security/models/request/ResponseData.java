package mcc3.com.security.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author firmanzega
 */

/*
    ResponseData ini adalah class yang nantinya akan
    meng-enkapsulasi informasi yang akan kita kirimkan 
    dari server ke client yang memanggil.

    kalau misalnya ada informasi benar atau salah 
    (sukses atau gagal mem-validasi) itu nantinya akan
    di enkapsulasi objeknya berbentuk ResponseData.
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    
    // attribut ini nanti yang akan menampung statusnya true/false
    private boolean status;
    
    /*
        kalau seandainya ada error/exception pada sisi endpoint nya
        maka pesan-pesan errornya akan kita tampung didalam message.
    */
    private String message;
    
    /*
        apabila data berhasil disimpan, di endpoint kita akan
        return lagi. 
        data yang berhasil disimpan itu akan diresponse balik
        dan data tadi akan disimpan didalam payLoad.
    */
    private T payLoad;
    
}
