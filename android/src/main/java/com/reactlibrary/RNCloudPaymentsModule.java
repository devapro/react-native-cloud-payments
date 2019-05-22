
package com.reactlibrary;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import ru.cloudpayments.sdk.CardFactory;
import ru.cloudpayments.sdk.ICard;

public class RNCloudPayments extends ReactContextBaseJavaModule {

    private Promise promise;

    public RNCloudPayments(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNCloudPayments";
    }

    @ReactMethod
    public void checkCard(ReadableMap config, Promise promise) {
        Boolean test = false;
        try{
            String cardNumber = "";
            if (config.hasKey("cardNumber")) {
                cardNumber = config.getString("cardNumber");
            }
            test = ru.cloudpayments.sdk.CardFactory.create(cardNumber.replaceAll(" ", "")).isValidNumber();
        } catch (Exception e){}
        if(!test){
            promise.reject("-1", "Invalid card number");
        } else {
            promise.resolve(null);
        }

    }

    @ReactMethod
    public void createToken(ReadableMap config, Promise promise) {
        String cardNumber = "";
        if (config.hasKey("cardNumber")) {
            cardNumber = config.getString("cardNumber");
        }
        String expDate = "";
        if (config.hasKey("expDate")) { // monthyear
            expDate = config.getString("expDate");
        }
        String cvv = "";
        if (config.hasKey("cvv")) {
            cvv = config.getString("cvv");
        }
        String CP_PUBLIC_ID = "";
        if (config.hasKey("CP_PUBLIC_ID")) {
            CP_PUBLIC_ID = config.getString("CP_PUBLIC_ID");
        } else {
            promise.reject("-1", "CP_PUBLIC_ID is required");
        }
        ICard card = CardFactory.create(cardNumber.trim(), expDate.trim(), cvv.trim());
        try {
            String hash = card.cardCryptogram(CP_PUBLIC_ID);
            promise.resolve(hash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        } catch (BadPaddingException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            promise.reject("-1", e.getMessage());
        }
    }
}