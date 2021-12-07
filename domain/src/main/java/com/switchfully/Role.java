package com.switchfully;

public enum Role {
    CUSTOMER(1), ADMIN(2);

    public final int rank;

    Role(int rank) {
        this.rank = rank;
    }
}
