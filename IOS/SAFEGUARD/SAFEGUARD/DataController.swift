//
//  DataController.swift
//  SAFEGUARD
//
//  Created by Kiran Kunigiri on 5/11/17.
//  Copyright © 2017 Tinovation. All rights reserved.
//

import Foundation
import Firebase

class DataController {
    
    static var instance = DataController()
    
    var ref: FIRDatabaseReference!
    var currentUser: String?
    
    init() {
        ref = FIRDatabase.database().reference()
    }
    
    func signupUser(username: String, password: String) {
        ref.child(username).observeSingleEvent(of: .value, with: { (snapshot) in
            
            if snapshot.exists() {
                // Username already taken
            } else {
                // Create new user
                self.ref.child(username).child("password").setValue(password)
                self.ref.child(username).child("stream").child("\(Date())").setValue("USER \(username) created")
            }
        }) { (error) in
            print(error.localizedDescription)
        }
    }
    
    func loginUser(username: String, password: String) {
        
    }
}
