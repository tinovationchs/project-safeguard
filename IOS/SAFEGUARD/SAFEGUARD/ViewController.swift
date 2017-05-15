//
//  ViewController.swift
//  SAFEGUARD
//
//  Created by Kiran Kunigiri on 5/11/17.
//  Copyright © 2017 Tinovation. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

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
        
        DataController.instance.signupUser(username: "sdfsdf", password: "test123")
    }

    @IBAction func signupButtonTapped(_ sender: UIButton) {
        let username = usernameTextField.text
        let password = passwordTextField.text
        if (username?.characters.count)! > K.minUsernameLength && (password?.characters.count)! > K.minPasswordLength {
            DataController.instance.signupUser(username: username!, password: password!)
        }
    }
    
    @IBAction func loginButtonTapped(_ sender: UIButton) {
        let username = usernameTextField.text
        let password = passwordTextField.text
        if (username?.characters.count)! > K.minUsernameLength && (password?.characters.count)! > K.minPasswordLength {
            DataController.instance.loginUser(username: username!, password: password!)
        }
    }
    
    
    
    // Allow users to tap outside to dismiss the keyboard
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesBegan(touches, with: event)
        usernameTextField.resignFirstResponder()
        passwordTextField.resignFirstResponder()
    }

}

extension ViewController: UITextFieldDelegate {
    
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

