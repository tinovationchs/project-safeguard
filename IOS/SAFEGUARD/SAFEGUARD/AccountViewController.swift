//
//  ViewController.swift
//  SAFEGUARD
//
//  Created by Kiran Kunigiri on 5/11/17.
//  Copyright © 2017 Tinovation. All rights reserved.
//

import UIKit
import ProgressHUD

class AccountViewController: UIViewController {

    // Outlets
    @IBOutlet var usernameTextField: UITextField!
    @IBOutlet var passwordTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // UI Setup
        usernameTextField.autocorrectionType = .no
        usernameTextField.delegate = self
        passwordTextField.autocorrectionType = .no
        passwordTextField.delegate = self
    }

    @IBAction func signupButtonTapped(_ sender: UIButton) {
        let username = usernameTextField.text
        let password = passwordTextField.text
        if (username?.characters.count)! > K.minUsernameLength && (password?.characters.count)! > K.minPasswordLength {
            DataController.instance.signupUser(username: username!, password: password!, completion: { (error) in
                if error == nil {
                    // Success
                    self.clearTextFields()
                    self.performSegue(withIdentifier: "loginSegue", sender: self)
                } else {
                    // Error
                    ProgressHUD.showError(error!)
                }
            })
        } else {
            ProgressHUD.showError("Username and password must be more than 3 characters long")
        }
    }
    
    @IBAction func loginButtonTapped(_ sender: UIButton) {
        let username = usernameTextField.text
        let password = passwordTextField.text
        if (username?.characters.count)! > K.minUsernameLength && (password?.characters.count)! > K.minPasswordLength {
            DataController.instance.loginUser(username: username!, password: password!, completion: { (error) in
                if error == nil {
                    // Success
                    self.clearTextFields()
                    self.performSegue(withIdentifier: "loginSegue", sender: self)
                } else {
                    // Error
                    ProgressHUD.showError(error!)
                }
            })
        } else {
            ProgressHUD.showError("We could not find an account with that username and password")
        }
    }
    
    
    
    // Allow users to tap outside to dismiss the keyboard
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesBegan(touches, with: event)
        usernameTextField.resignFirstResponder()
        passwordTextField.resignFirstResponder()
    }
    
    // Clear all text fields
    func clearTextFields() {
        usernameTextField.text = nil
        passwordTextField.text = nil
    }

}

extension AccountViewController: UITextFieldDelegate {
    
    // Delegate the return key action
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField == usernameTextField {
            passwordTextField.becomeFirstResponder()
        } else if textField == passwordTextField {
            textField.resignFirstResponder()
        }
        return false
    }
    
}

