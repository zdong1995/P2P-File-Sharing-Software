# CNT 5106C - P2P File Sharing Software
Java implementation of a P2P file sharing software similar to BitTorrent. BitTorrent is a popular P2P protocol for file distribution. Among its interesting features, you are asked to implement the choking-unchoking mechanism which is one of the most important features of BitTorrent.

### Project Structure
#### Package Layout
```
src                        
├── Common.cfg              
├── PeerInfo.cfg            
└── p2p                     
    ├── config              
    │    ├── CommonConfig.java
    |    └── PeerInfo.java   
    ├── connection          
    │    └── ConnectionHandler.java 
    ├── peer                
    │    ├── Peer.java       
    │    ├── PeerProcess.java
    │    └── Process.java    
    └── sample              
         ├── Client.java     
         └── Server.java
```
#### UML Diagram
![](https://github.com/zdong1995/P2P-File-Sharing-Software/blob/master/Images/UML.png)

### Test
You need to start the peer processes in the order specified in the file `PeerInfo.cfg` on the machine specified in the file. You should specify the peer ID as a parameter.
For example, given the above `PeerInfo.cfg` file, to start the first peer process, you should test with the following two methods:

#### Method 1: Command line
1. Change directory to `src`
2. Run `javac p2p/peer/PeerProcess.java` to compile the class
3. Run `java p2p/peer/PeerProcess 1001`

#### Method 2: Use IDEs configurations (recommended) 
If you use IDEs as IntelliJ, the IDE will compile the class automatically. You don't need to `javac` every time. 
1. Just set up the running configuration as following:
![](https://github.com/zdong1995/P2P-File-Sharing-Software/blob/master/Images/cmd-setup-1.png)
![](https://github.com/zdong1995/P2P-File-Sharing-Software/blob/master/Images/cmd-setup-2.png)
2. Click the green run button, then you will see the result:
![](https://github.com/zdong1995/P2P-File-Sharing-Software/blob/master/Images/test-without-new-thread.png)
3. Be careful, if you create new thread when running the `PeerProcss` here, the run result window will be like this:
![](https://github.com/zdong1995/P2P-File-Sharing-Software/blob/master/Images/test-with-new-thread.png)
4. Before next time run again, you need to click stop to terminate all the threads.