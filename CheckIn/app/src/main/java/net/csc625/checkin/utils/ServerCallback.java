package net.csc625.checkin.utils;


import net.csc625.checkin.models.exceptions.ErrorResponse;

public interface ServerCallback<T> {
        void onSuccess(T result);
        void onFail(ErrorResponse errorMessage);
}
