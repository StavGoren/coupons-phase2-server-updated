package com.stav.server.controllers;

import com.stav.server.entities.Purchase;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.logic.PurchasesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    private PurchasesLogic purchasesLogic;

    @Autowired
    public PurchasesController(PurchasesLogic purchasesLogic) {
        this.purchasesLogic = purchasesLogic;
    }

    @PostMapping
    public void createPurchase(@RequestBody Purchase purchase) throws ServerException {
        this.purchasesLogic.createPurchase(purchase);
    }

    @PutMapping
    public void updatePurchase(@RequestBody Purchase purchase) {
        // Ask if mandatory
    }

    @GetMapping("{purchaseId}")
    public Optional<Purchase> getPurchaseById(@PathVariable("purchaseId") int id) throws ServerException{
        return purchasesLogic.getPurchase(id);
    }

//    @GetMapping("/byPage")
//    public List<Purchase> getPurchasesByPage(@RequestParam("pageNumber") int pageNumber) throws ServerException{
//        List<Purchase> purchases = purchasesLogic.getPurchasesByPage(pageNumber);
//        return purchases;
//    }

    @DeleteMapping("{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") int id) throws ServerException{
        purchasesLogic.removePurchaseById(id, UserType.Admin);
    }
}
