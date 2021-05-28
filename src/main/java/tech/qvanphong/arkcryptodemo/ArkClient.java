package tech.qvanphong.arkcryptodemo;

import com.google.common.collect.Lists;
import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.networks.Mainnet;
import org.arkecosystem.crypto.transactions.types.Transaction;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ArkClient {
    private final Connection connection;

    public ArkClient() {
        Network.set(new Mainnet());
        HashMap<String, Object> configurations = new HashMap<>();
        configurations.put("host", "http://178.128.123.47:4003/api/");
        configurations.put("content-type", "application/json");
        connection = new Connection(configurations);
    }

    public long getNonce() throws IOException, NullPointerException, NumberFormatException {
        LinkedTreeMap<String, Object> wallet = connection.api()
                .wallets
                .show(WalletConstants.senderWallet);
        if (wallet.get("data") == null) {
            throw new NullPointerException("Wallet not found");
        } else {
            try {
                return Long.parseLong(((LinkedTreeMap) wallet.get("data")).get("nonce").toString());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Can't parse nonce");
            }
        }
    }

    public boolean broadCastTransaction(Transaction transaction) {
        ArrayList<HashMap> payload = new ArrayList<>();
        payload.add(transaction.toHashMap());

        LinkedTreeMap<String, Object> broadcastResponse = null;
        try {
            broadcastResponse = connection.api().transactions.create(payload);
            System.out.println(broadcastResponse);


            return broadcastResponse.get("errors") == null;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }
}
