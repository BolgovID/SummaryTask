package ua.nure.bolhov.SummaryTask4.db;

import ua.nure.bolhov.SummaryTask4.db.entity.User;

public enum UserStatus {
    UNBUNNED, BUNNED, PENALTY;

    public static UserStatus getStatus(User user){
        return UserStatus.values()[(int) (user.getIdStatus()-1)];
    }


    public static UserStatus getStatus(long id){
        return UserStatus.values()[(int) (id-1)];
    }

    public long getNumber(){
        return this.ordinal()+1;
    }

}
