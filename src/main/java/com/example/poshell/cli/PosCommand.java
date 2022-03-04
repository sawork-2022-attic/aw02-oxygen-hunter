package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "View Products in the Cart", key = "v")
    public String viewCart() {
        return posService.viewCart();
    }

    @ShellMethod(value = "Empty the Cart", key = "e")
    public String emptyCart() {
        posService.emptyCart();
        return "Cart is empty now";
    }

    @ShellMethod(value = "Modify the Cart", key = "m")
    public String modifyCart(String productId, int amount) {
        if (posService.modifyCart(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Total Cost", key = "t")
    public String totalCost() {
        return "Total cost is: " + Double.toString(posService.total(posService.getCart()));
    }

    @ShellMethod(value = "Checkout", key = "c")
    public String checkout() {
        String paid = Double.toString(posService.total(posService.getCart())) + " is paid";
        posService.emptyCart();
        return paid;
    }
}
