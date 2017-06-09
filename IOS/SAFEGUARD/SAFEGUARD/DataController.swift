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
    
    // Singleton
    static var instance = DataController()
    
    // Properties
    var ref: FIRDatabaseReference!
    var currentUser: String?
    
    
    // Initializer
    init() {
        ref = FIRDatabase.database().reference()
    }
    
    
    /** Signs up a user with the given credentials */
    func signupUser(username: String, password: String, completion: @escaping (String?) -> Void) {
        ref.child(username).observeSingleEvent(of: .value, with: { (snapshot) in
            
            if snapshot.exists() {
                // Username already taken
                completion("Username already taken")
            } else {
                // Create new user
                self.ref.child(username).child("password").setValue(password)
                self.ref.child(username).child("stream").child("\(Date.currentDateToString())").setValue("Account \(username) created")
                self.currentUser = username
                completion(nil)
            }
        }) { (error) in
            print(error.localizedDescription)
            completion(error.localizedDescription)
        }
    }
    
    /** Logs a user in with the given credentials */
    func loginUser(username: String, password: String, completion: @escaping (String?) -> Void) {
        ref.child(username).observeSingleEvent(of: .value, with: { (snapshot) in
            
            if snapshot.exists() {
                // User exists - sign in
                self.currentUser = username
                completion(nil)
            } else {
                // User does not exist or wrong credentials entered
                completion("We could not find an account with that username and password")
            }
        }) { (error) in
            print(error.localizedDescription)
            completion(error.localizedDescription)
        }
    }
    
    /** Logs the user out */
    func logoutUser() {
        currentUser = nil
    }
    
    /** Gets the list of events */
    func getEventList(completion: @escaping ([Event]) -> Void) {
        var eventList: [Event] = []
        if currentUser == nil {
            completion([])
        }
        
        ref.child(currentUser!).child("stream").observeSingleEvent(of: .value, with: { (snapshot) in
            for child in snapshot.children.allObjects as! [FIRDataSnapshot] {
                let date = Date.dateToReadableString(dateString: child.key)
                let event = Event(name: child.value as! String, date: date)
                eventList.append(event)
            }
            completion(eventList)
        }) { (error) in
            print(error.localizedDescription)
            completion([])
        }
    }
}




extension Date {
    
    static func currentDateToString() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd-hh-mm-a"
        return dateFormatter.string(from: Date())
    }
    
    static func dateToReadableString(dateString: String) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd-hh-mm-a"
        print(dateString)
        let date = dateFormatter.date(from: dateString)!
        
        let readableDateFormatter = DateFormatter()
        readableDateFormatter.dateFormat = "MMMM d, hh:mm a"
        return readableDateFormatter.string(from: date)
    }
    
}




