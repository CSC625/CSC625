package net.csc625.checkin.utils;


import net.csc625.checkin.models.exceptions.ErrorResponse;

/**
 * Created by brocktubre on 1/31/18.
 */

public interface ServerCallback<T> {
        void onSuccess(T result);
        void onFail(ErrorResponse errorMessage);
}
