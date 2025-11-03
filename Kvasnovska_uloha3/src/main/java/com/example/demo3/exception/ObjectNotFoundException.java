package com.example.demo3.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String what, Long id) {
        super(what + " s ID " + id + " nebol nájdený.");
    }

    // Ak chceš podporu aj pre Integer ID (napríklad ak máš zmiešané typy):
    public ObjectNotFoundException(String what, Integer id) {
        super(what + " s ID " + id + " nebol nájdený.");
    }
}
