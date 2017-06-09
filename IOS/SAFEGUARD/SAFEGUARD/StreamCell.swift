//
//  StreamCell.swift
//  SAFEGUARD
//
//  Created by Kiran Kunigiri on 6/8/17.
//  Copyright © 2017 Tinovation. All rights reserved.
//

import UIKit

class StreamCell: UICollectionViewCell {
    
    @IBOutlet var eventLabel: UILabel!
    @IBOutlet var dateLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        // Corner
        self.contentView.layer.cornerRadius = 4
        self.contentView.layer.masksToBounds = true
        self.clipsToBounds = false
        
        // Shadow
        self.layer.shadowColor = UIColor.black.cgColor
        self.layer.shadowOpacity = 0.5
        self.layer.shadowOffset = CGSize(width: 0, height: 1)
        self.layer.shadowRadius = 1
    }
    
}
