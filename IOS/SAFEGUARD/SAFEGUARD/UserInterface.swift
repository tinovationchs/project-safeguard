//
//  UserInterface.swift
//  SAFEGUARD
//
//  Created by Kiran Kunigiri on 5/11/17.
//  Copyright © 2017 Tinovation. All rights reserved.
//

import UIKit


/** A rounded button */
class SGButton: UIButton {
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.cornerRadius = self.frame.height/2
    }
    
}



/** A text field with a bottom border only */
class SGTextField: UITextField {

    override func draw(_ rect: CGRect) {
        
        // Colors
        self.backgroundColor = UIColor.clear
        
        //Borders
        self.borderStyle = .none
        self.clipsToBounds = false
        
        // Add the bottom line
        let lineFrame = CGRect(x: 0, y: self.frame.height - 8, width: self.frame.width, height: 1.5)
        let lineView = UIView(frame: lineFrame)
        lineView.backgroundColor = UIColor.black
        self.addSubview(lineView)
    }
    
    
}
