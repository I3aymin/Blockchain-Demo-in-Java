import java.util.Date;

public class Block {

	//Standard Variables to be utilized
	private String hash;
	private String previousHash;
	private String data;
	private long timeStamp;
	private int nonce = 0;
	
	//Setting and getting values 
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateBlockHash();
	}
	
	public String calculateBlockHash() {
		String calculatedHash = Crypt.sha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
		
		return calculatedHash;
	}
	
	public void mineBlockHash(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		System.out.println("The hash value: " + hash);
		System.out.println("The target: " + hash.substring(0, difficulty));
		while(!hash.substring(0, difficulty).contentEquals(target)) {
			nonce++;
			hash = calculateBlockHash();
		}
		System.out.println("Block has been Mined: " + hash);
	}
	
	public String getHash() {
		return hash;
	}
	
	public String getPreviousHash() {
		return previousHash;
	}
}
