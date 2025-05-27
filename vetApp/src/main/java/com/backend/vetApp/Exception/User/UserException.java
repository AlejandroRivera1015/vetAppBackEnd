package com.backend.vetApp.Exception.User;

public class UserException extends  Exception {
   private Long userId;

   public  UserException(String message, Long userId){
         super(message);
         this.userId = userId;
   }
}
