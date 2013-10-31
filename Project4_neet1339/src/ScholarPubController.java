import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ScholarPubController {
	private ScholarPubModel model;
	private ScholarPubInputWindow inputView;

	public ScholarPubController() {

	}
	
	private class mainSortButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("action ");
			if(inputView.mainFrameVis()){
				inputView.setMainFrameNotVis();
			}
			inputView.displaySortPane();
		}

	}

	private class SortBIButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.fillDirectory();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.sortRouting(2);
			model.output();
		System.out.println("You got it!");
		}
		
	}
	
	
	public void setModel(ScholarPubModel model){
		this.model = model;
	}
	
	public void setInputWindow(ScholarPubInputWindow inputView){
		this.inputView = inputView;
		
		//Register listeners
		inputView.getSortButton().addActionListener(new mainSortButtonListener());
		inputView.getBIButton().addActionListener(new SortBIButtonListener());
	}
	
}
