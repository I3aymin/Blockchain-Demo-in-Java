import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class StoringBlocks {

	public static int difficulty = 5;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	
	public static void main(String[] args) {
		
		String previousHash = "0";
		for(int i = 0; i < 4; i++) {
			Block block = new Block("Hi, this is in the " + i + " block.", previousHash);
			blockchain.add(block);
			
			System.out.println("Trying to Mine block " + i + " ... ");
			block.mineBlockHash(difficulty);
			
			previousHash = block.getHash();
		}
		
		System.out.println("\nBlockchain is valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe blockchain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			
			if(!currentBlock.getHash().equals(currentBlock.calculateBlockHash())) {
				System.out.println("Current hashes are not equal");
				return false;
			}
			
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
				System.out.println("Previous hashes are not equal");
				return false;
			}
			
			if(!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block has not been mined");
				return false;
			}
		}
		return true;
	}
}
