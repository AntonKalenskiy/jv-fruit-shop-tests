package core.basesyntax.service.strategy;

import static org.junit.Assert.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.AfterClass;
import org.junit.Test;

public class PurchaseOperationHandlerTest {
    @Test
    public void getCorrectPurchaseOperation_Ok() {
        Storage.fruits.put("banana", 100);

        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setFruit("banana");
        fruitTransaction.setQuantity(50);

        OperationHandler operationHandler = new PurchaseOperationHandler();
        operationHandler.operate(fruitTransaction);

        int bananaAmount = Storage.fruits.get("banana");
        assertEquals(50, bananaAmount);
    }

    @Test(expected = RuntimeException.class)
    public void nullArgument_NotOk() {
        new PurchaseOperationHandler().operate(null);
    }

    @AfterClass
    public static void afterClass() {
        Storage.fruits.clear();
    }
}