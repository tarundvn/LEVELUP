import java.util.*;

class Solution{
    public static int countStudents(List<Integer> stu, List<Integer> san) {
        Queue<Integer> qe = new ArrayDeque<>();
		int stu1=0;
		int stu0=0;
		for(int i:stu){
			if(i==0){
				stu0++;
			}else{
				stu1++;
			}
			qe.add(i);
		}
		int san1=0;
		int san0=0;
		for(int i : san){
			if(i==0){
				san0++;
			}else{
				san1++;
			}
		}
		System.out.println(stu1+" "+ stu0+" "+san0+" "+san1);
		int i=0;
		while(qe.size()>0 && i<san.size()){
			if(stu0 == qe.size() && san.get(i) == 1){
				break;
			}
			if(stu1 == qe.size() && san.get(i) == 0){
				break;
			}
			 int a = qe.remove();
			if(a==san.get(i)){
				i++;
				if(a==0){
					stu0--;
					san0--;
				}else{
					stu1--;
					san1--;
					
				}
			}else{
				qe.add(a);
			}
		}
		return qe.size();
    }
}
public class l002{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Integer>students=new ArrayList<>();
        for(int i=0;i<n;i++){
            students.add(sc.nextInt());
        }
        List<Integer>sandwiches=new ArrayList<>();
        for(int j=0;j<n;j++){
            sandwiches.add(sc.nextInt());
        }
        System.out.println(Solution.countStudents(students,sandwiches));
        sc.close();
    }
}