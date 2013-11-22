/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import javax.ejb.Stateless;

import com.wizglobal.vehicletracker.domain.User;

/**
 * @author Otieno Lawrence
 * 
 */
@Stateless
public class UserService extends DataAccessService<User>{
   
   public UserService(){
       super(User.class);
   }   
}
