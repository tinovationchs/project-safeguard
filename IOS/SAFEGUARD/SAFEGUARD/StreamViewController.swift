//
//  StreamViewController.swift
//  SAFEGUARD
//
//  Created by Kiran Kunigiri on 6/8/17.
//  Copyright © 2017 Tinovation. All rights reserved.
//

import UIKit

class StreamViewController: UIViewController {

    // Outlets
    @IBOutlet var nameLabel: UILabel!
    @IBOutlet var friendLabel: UILabel!
    @IBOutlet var strangerLabel: UILabel!
    @IBOutlet var streamCollectionView: UICollectionView!
    
    var eventList: [Event] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Setup tableview
        streamCollectionView.delegate = self
        streamCollectionView.dataSource = self
        
        // Setup UI
        nameLabel.text = DataController.instance.currentUser
        friendLabel.text = "0 friends"
        strangerLabel.text = "0 strangers"
        
        DataController.instance.getEventList { (events) in
            // Setup event list
            self.eventList = events
            self.streamCollectionView.reloadData()
        }
    }

    @IBAction func logoutButtonTapped(_ sender: UIButton) {
        DataController.instance.logoutUser()
        self.dismiss(animated: true, completion: nil)
    }
}



extension StreamViewController: UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return eventList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "streamCell", for: indexPath) as! StreamCell
        cell.eventLabel.text = eventList[indexPath.item].name
        cell.dateLabel.text = eventList[indexPath.item].date
        return cell
    }
    
}



extension StreamViewController: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: self.view.frame.width - 60, height: 100)
    }
    
}





