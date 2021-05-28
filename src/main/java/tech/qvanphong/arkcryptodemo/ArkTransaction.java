package tech.qvanphong.arkcryptodemo;

import org.arkecosystem.crypto.transactions.builder.TransferBuilder;
import org.arkecosystem.crypto.transactions.types.Transaction;

public class ArkTransaction {
    private final long  FEE = 600000;
    public Transaction createTransaction(long amount, String recipientId, long nonce){
        return new TransferBuilder()
                .amount(amount)
                .fee(FEE)
                .nonce(++nonce)
                .vendorField("From Phong with love")
                .recipient(recipientId)
                .sign("wallet_pass").transaction;

    }
}
