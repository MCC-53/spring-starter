/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Controllers;

import Mcc53.ClientApp.Models.ErrorHandlingPage;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author putug
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            ErrorHandlingPage error = new ErrorHandlingPage();
            error.setStatus(Integer.valueOf(status.toString()));

            if (error.getStatus() == HttpStatus.NOT_FOUND.value()) {
                error.setMessage("Data tidak ditemukan");
            } else if (error.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                error.setMessage("Ouch ada yang salah pada server");
            }

            model.addAttribute("error", error);
        }
        return "errorPage";
    }
}
