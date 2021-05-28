package tech.qvanphong.arkcryptodemo;

import org.arkecosystem.crypto.transactions.types.Transaction;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ArkTransaction arkTransaction = new ArkTransaction();
        ArkClient arkClient = new ArkClient();

        Transaction transaction = null;
        try {
            transaction = arkTransaction.createTransaction((long) (1 * Math.pow(10, 7)), WalletConstants.recipientWallet, arkClient.getNonce());
            System.out.println(transaction);

            if (arkClient.broadCastTransaction(transaction)) {
                System.out.println("Broadcast transaction completed");
            } else {
                System.out.println("Broadcast transaction failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
