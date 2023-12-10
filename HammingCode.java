import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class HammingCode {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		Calculator c = new Calculator();
		System.out.print("Input binary string: ");
		String hcode = sc.next();
		System.out.print("Parity [e]even / [o]odd]: ");
		char par = sc.next().charAt(0);
		char par2;
		if(par=='e') {
			par='1';par2='0';
		}else {
			par='0';par2='1';
		}
		
		char[] hamCodeArr=hcode.toCharArray();
		int redundantBits = c.countRedundant(hamCodeArr.length);	
		c.editArr(hamCodeArr,par,par2);
		
	}
	
}

class Calculator{												//redundant bits or hamming bits
	int redBits;
	public int countRedundant(int arrLength) {
		for(int i=0;i<arrLength;i++) {	
			if ((int)Math.pow(2, i)>=arrLength+i+1) {
				redBits= i;	
				return i;
			}
		}
		return 0;		
	}
	
	public void editArr(char [] charArr,char par1,char par2) {
		ArrayList<Character> editedArray = new ArrayList<Character>();
		int n=0,ind=0;
		for(int x =1;x<=redBits+charArr.length;x++) {
			if(x==(int)Math.pow(2, n)&&n<redBits) {
				editedArray.add('H');
				n++;
			}else {
				editedArray.add(charArr[ind]);
				ind++;
			}
			
		}
		System.out.println(editedArray);
		
		int pos,loop,x,count=0;
		for (int i=0;i<redBits;i++) {
			pos=(int)Math.pow(2, i);
			
			for(int y=pos;y<=editedArray.size();y+=pos*2) {
				loop=pos;
				x=y;
				
				while(loop>0) {
					if(editedArray.get(x-1)=='1') {
						count++;
					}
					if(x>editedArray.size()-1) {
						break;
					}
					x++;
					loop--;
					
				}
				
			}
			
			if(count%2!=0) {
				editedArray.set(pos-1,par1);
			}else {
				editedArray.set(pos-1,par2);
			}
			count=0;
		}
		
		System.out.println("\nHamming Code: \n"+editedArray);
	}		
}