/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import khoita.detailcart.DetailCartDAO;
import khoita.detailcart.DetailCartDTO;
import khoita.product.ProductDAO;
import khoita.product.ProductDTO;
import khoita.registration.RegistrationDTO;

/**
 *
 * @author Fstore
 */
public class testingConnect {

    public static void main(String[] args) throws NamingException {
        ProductDAO dao = new ProductDAO();
        RegistrationDTO user = new RegistrationDTO("khoi123", "1432", "hhahaha", true);
        ProductDTO dto = new ProductDTO("PRD02", "Product2", "This is product 2", (float) 1234.3, 1);
        DetailCartDAO dtdao = new DetailCartDAO();
        float nnn = (float) 12.323222;
        System.out.println(Math.round(nnn*10.0)/10.0);
//        try {
//            
//            
////            if (dtdao.updateItemFromCart("PRD05","2537",5)) {
////                System.out.println("hahaha");
////            } else {
////                System.out.println("ngu");
////            }
//
////            if(dtdao.deleteItemFromCart(dto.getProductID(), "2993")){
////                System.out.println("hahaaha");
////            }else{
////                System.out.println("ngu");
////            };
////            dao.getProductFromCartSQL(user.getAccount());
////            List<DetailCartDTO> product = dao.getProductFromCart();
////            for (DetailCartDTO detailCartDTO : product) {
////                System.out.println(detailCartDTO);
////            }
//        } //            dao.getProductList();
//        //            Map<String,ProductDTO> product = dao.getProduct();
//        //            for (String string : product.keySet()) {
//        //                System.out.println(product.get(string).getProductName());
//        //            }
//        catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            System.out.println(ex);
//        }
    }
}
