package edu.siu.movie;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.AbstractAction;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.Action;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JScrollPane;



public class WindowBuilder extends JFrame {
	
	static LinkedList<MovieObjectBuilder> WantToSeeList = new LinkedList<MovieObjectBuilder>();
	static LinkedList<MovieObjectBuilder> NewReleasesList = new LinkedList<MovieObjectBuilder>();
	static LinkedList<MovieObjectBuilder> RecommendationList = new LinkedList<MovieObjectBuilder>();
	static LinkedList<MovieObjectBuilder> HaveSeenList = new LinkedList<MovieObjectBuilder>();
	
	
	JList haveSeenList = new JList();
	JList newReleasesList = new JList();
	JList recomendationsList = new JList();
	JList wantToSeeList = new JList();
	
	
	private JPanel contentPane;
	private JTextField haveSeenTextField;
	private JTextField wantToSeeTextField;
	private JTextField haveSeenRatingText;
	JTextPane haveSeenTextPane = new JTextPane();
	JTextPane wantToSeeTextPane = new JTextPane();
	JTextPane newReleasesTextPane = new JTextPane();
	JTextPane recomendationsTextPane = new JTextPane();
	

	DefaultListModel dlm_WantToSee = new DefaultListModel();
	DefaultListModel dlm_HaveSeen = new DefaultListModel();
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilder frame = new WindowBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public WindowBuilder() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel haveSeenPanel = new JPanel();
		tabbedPane.addTab("Have Seen", null, haveSeenPanel, null);
		
		haveSeenTextField = new JTextField();
		haveSeenTextField.setColumns(10);
		
		// HAVE SEEN
		
		
		JButton btnAdd_HaveSeen = new JButton("<< Add To List");
		btnAdd_HaveSeen.addActionListener(new ActionListener() {               
			public void actionPerformed(ActionEvent e) {
				actionAddToList_HaveSeen();
			}
		});
		
		
		
		
		JButton btnSearch_HaveSeen = new JButton("Search");
		btnSearch_HaveSeen.addActionListener(new ActionListener() {               
			public void actionPerformed(ActionEvent e) {
				actionHaveSeenSearch();
			}
		});
		
		
		
		haveSeenRatingText = new JTextField();
		haveSeenRatingText.setColumns(10);
		
		JButton btnAddYourRating = new JButton("Add Your Rating");
		btnAddYourRating.addActionListener(new ActionListener() {               
			public void actionPerformed(ActionEvent e) {
				actionAddYourRating();
			}

		});
		
		
		
		JButton btnDeleteFromList = new JButton("Delete From List");
		btnDeleteFromList.addActionListener(new ActionListener() {               
			public void actionPerformed(ActionEvent e) {
				actionDelete_HaveSeen();
			}

		});
		
		
		
		
		GroupLayout gl_haveSeenPanel = new GroupLayout(haveSeenPanel);
		gl_haveSeenPanel.setHorizontalGroup(
			gl_haveSeenPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_haveSeenPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_haveSeenPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_haveSeenPanel.createSequentialGroup()
							.addComponent(haveSeenRatingText, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAddYourRating))
						.addComponent(haveSeenList, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteFromList, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addGroup(gl_haveSeenPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_haveSeenPanel.createSequentialGroup()
							.addComponent(btnAdd_HaveSeen)
							.addGap(18)
							.addComponent(haveSeenTextField, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch_HaveSeen)
							.addGap(27))
						.addGroup(Alignment.TRAILING, gl_haveSeenPanel.createSequentialGroup()
							.addComponent(haveSeenTextPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(32))))
		);
		haveSeenTextPane.setEditable(false);
		
		
		
		
		haveSeenList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				MovieObjectBuilder movie = (MovieObjectBuilder) haveSeenList.getSelectedValue();
				Document doc = haveSeenTextPane.getDocument();
				if(!haveSeenList.isSelectionEmpty()){
			      try {
			    	haveSeenTextPane.setText("");
					doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
					doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
					doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
					doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
					doc.insertString(doc.getLength(), "My Rating: " + movie.getMyRating() + "\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		
		
		
		
		gl_haveSeenPanel.setVerticalGroup(
			gl_haveSeenPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_haveSeenPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_haveSeenPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd_HaveSeen)
						.addComponent(haveSeenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch_HaveSeen))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(haveSeenTextPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
				.addGroup(gl_haveSeenPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(haveSeenList, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_haveSeenPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(haveSeenRatingText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddYourRating))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnDeleteFromList)
					.addContainerGap())
		);
		haveSeenPanel.setLayout(gl_haveSeenPanel);
		
		
		
		
		
		
		
		//WANT TO SEE
		
		
		
		JPanel wantToSeePanel = new JPanel();
		tabbedPane.addTab("Want To See", null, wantToSeePanel, null);
		
		wantToSeeTextField = new JTextField();
		wantToSeeTextField.setColumns(10);
		
		JButton btnAdd_WantToSee = new JButton("<< Add To List");
		btnAdd_WantToSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAddToList_WantToSee();
			}
		});
		
		
		wantToSeeTextPane.setEditable(false);
		
		JButton btnSearch_WantToSee = new JButton("Search");
		btnSearch_WantToSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearch_WantToSee();
			}
		});
		
		
		JButton btnDelete_WantToSee = new JButton("Delete From List");
		btnDelete_WantToSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDelete_WantToSee();
			}
		});
		GroupLayout gl_wantToSeePanel = new GroupLayout(wantToSeePanel);
		gl_wantToSeePanel.setHorizontalGroup(
			gl_wantToSeePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_wantToSeePanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_wantToSeePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDelete_WantToSee, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addComponent(wantToSeeList, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_wantToSeePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_wantToSeePanel.createSequentialGroup()
							.addComponent(btnAdd_WantToSee)
							.addGap(18)
							.addComponent(wantToSeeTextField, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch_WantToSee)
							.addGap(27))
						.addGroup(Alignment.TRAILING, gl_wantToSeePanel.createSequentialGroup()
							.addComponent(wantToSeeTextPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(32))))
		);
		
		
		
		
		wantToSeeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				MovieObjectBuilder movie = (MovieObjectBuilder) wantToSeeList.getSelectedValue();
				Document doc = wantToSeeTextPane.getDocument();
				if(!wantToSeeList.isSelectionEmpty()){
			      try {
			    	wantToSeeTextPane.setText("");
					doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
					doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
					doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
					doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		
		
		gl_wantToSeePanel.setVerticalGroup(
			gl_wantToSeePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_wantToSeePanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_wantToSeePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd_WantToSee)
						.addComponent(wantToSeeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch_WantToSee))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(wantToSeeTextPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
				.addGroup(Alignment.TRAILING, gl_wantToSeePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(wantToSeeList, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnDelete_WantToSee)
					.addContainerGap())
		);
		wantToSeePanel.setLayout(gl_wantToSeePanel);
		
		
		
		
		
		
		
		
		// RECOMENDATIONS
		
		
		
		
		JPanel recomendationsPanel = new JPanel();
		tabbedPane.addTab("Recomendations", null, recomendationsPanel, null);
		
		JButton btnGetRecomendations = new JButton("Get Recomendations");
		btnGetRecomendations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionGetRecomendations();
			}
		});
		
		
		recomendationsTextPane.setEditable(false);
		
		
		
		JButton btnDelete_Recomendations = new JButton("Delete From List");
		btnDelete_Recomendations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDelete_Recomendations();
			}
		});
		GroupLayout gl_recomendationsPanel = new GroupLayout(recomendationsPanel);
		gl_recomendationsPanel.setHorizontalGroup(
			gl_recomendationsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_recomendationsPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_recomendationsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDelete_Recomendations, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addComponent(recomendationsList, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addGroup(gl_recomendationsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_recomendationsPanel.createSequentialGroup()
							.addComponent(recomendationsTextPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(32))
						.addGroup(gl_recomendationsPanel.createSequentialGroup()
							.addComponent(btnGetRecomendations, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(100))))
		);
		
		
		recomendationsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				MovieObjectBuilder movie = (MovieObjectBuilder) recomendationsList.getSelectedValue();
				Document doc = recomendationsTextPane.getDocument();
				if(!recomendationsList.isSelectionEmpty()){
			      try {
			    	recomendationsTextPane.setText("");
					doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
					doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
					doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
					doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		
		
		
		gl_recomendationsPanel.setVerticalGroup(
			gl_recomendationsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_recomendationsPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(btnGetRecomendations)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(recomendationsTextPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
				.addGroup(Alignment.TRAILING, gl_recomendationsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(recomendationsList, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(btnDelete_Recomendations)
					.addContainerGap())
		);
		recomendationsPanel.setLayout(gl_recomendationsPanel);
		
		
		
		//   NEW RELEASES  
		
		
		
		JPanel newReleasesPane = new JPanel();
		tabbedPane.addTab("New Releases", null, newReleasesPane, null);
		
		JButton btnGetNewReleases = new JButton("Get New Releases");
		btnGetNewReleases.addActionListener(new ActionListener() {               
			public void actionPerformed(ActionEvent e) {
				actionGetNewReleases();
			}

		});
		
		
		JButton btnDelete_NewReleases = new JButton("Delete From List");
		btnDelete_NewReleases.addActionListener(new ActionListener() {               
			public void actionPerformed(ActionEvent e) {
				actionDelete_NewReleases();
			}

		});
		GroupLayout gl_newReleasesPane = new GroupLayout(newReleasesPane);
		gl_newReleasesPane.setHorizontalGroup(
			gl_newReleasesPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newReleasesPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_newReleasesPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDelete_NewReleases, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_newReleasesPane.createSequentialGroup()
							.addGap(8)
							.addComponent(newReleasesList, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_newReleasesPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_newReleasesPane.createSequentialGroup()
							.addComponent(newReleasesTextPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(32))
						.addGroup(gl_newReleasesPane.createSequentialGroup()
							.addComponent(btnGetNewReleases, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(100))))
		);
		newReleasesTextPane.setEditable(false);
		gl_newReleasesPane.setVerticalGroup(
			gl_newReleasesPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_newReleasesPane.createSequentialGroup()
					.addGap(21)
					.addComponent(btnGetNewReleases)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(newReleasesTextPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
				.addGroup(gl_newReleasesPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(newReleasesList, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(btnDelete_NewReleases)
					.addContainerGap())
		);
		
		
		
		
		
		newReleasesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				MovieObjectBuilder movie = (MovieObjectBuilder) newReleasesList.getSelectedValue();
				Document doc = newReleasesTextPane.getDocument();
				if(!newReleasesList.isSelectionEmpty()){
			      try {
			    	newReleasesTextPane.setText("");
					doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
					doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
					doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
					doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		newReleasesPane.setLayout(gl_newReleasesPane);
	}
	
	
	
	
	
	
	
	
	
	//  HAVE SEEN
	
	private void actionHaveSeenSearch(){
		String title = haveSeenTextField.getText();
		if(title != null && !title.isEmpty()){
			MovieObjectBuilder movie = new MovieObjectBuilder(title);
			Document doc = haveSeenTextPane.getDocument();
			haveSeenTextPane.setText("");
			try {
				doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
				doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
				doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
				doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
				doc.insertString(doc.getLength(), "My Rating: " + movie.getMyRating() + "\n", null);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			
		}
			
	}
	
	private void actionAddToList_HaveSeen(){
		String title = haveSeenTextField.getText();
		MovieObjectBuilder movie = new MovieObjectBuilder(title);
		dlm_HaveSeen.addElement(movie);
		haveSeenList.setModel(dlm_HaveSeen);
		haveSeenTextField.setText("");
	}
	
	private void actionAddYourRating(){
		String rating = haveSeenRatingText.getText();
		int index = haveSeenList.getSelectedIndex();
		MovieObjectBuilder movie = (MovieObjectBuilder) dlm_HaveSeen.getElementAt(index);
		movie.setMyRating(rating);
		HaveSeenList.add(movie);
		dlm_HaveSeen.setElementAt(movie, index);
		Document doc = haveSeenTextPane.getDocument();
		haveSeenTextPane.setText("");
		haveSeenRatingText.setText("");
		try {
			doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
			doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
			doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
			doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
			doc.insertString(doc.getLength(), "My Rating: " + movie.getMyRating() + "\n", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void actionDelete_HaveSeen(){
		int index = haveSeenList.getSelectedIndex();
		if(index >=0){
			haveSeenTextPane.setText("");
			((DefaultListModel) haveSeenList.getModel()).removeElementAt(index);
		}
	}
	
	
	
	
	
	
	
	//   WANT TO SEE
	
	private void actionSearch_WantToSee(){
		String title = wantToSeeTextField.getText();
		if(title != null && !title.isEmpty()){
			MovieObjectBuilder movie = new MovieObjectBuilder(title);
			Document doc = wantToSeeTextPane.getDocument();
			wantToSeeTextPane.setText("");
			try {
				doc.insertString(doc.getLength(), "Title: " + movie.getMovieTitle() + "\n", null);
				doc.insertString(doc.getLength(), "Release Date: " + movie.getReleaseDate() + "\n", null);
				doc.insertString(doc.getLength(), "IMDB Rating: " + movie.getIMDBRating() + "\n", null);
				doc.insertString(doc.getLength(), "Plot: " + movie.getPlot() + "\n", null);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void actionAddToList_WantToSee(){
		String title = wantToSeeTextField.getText();
		MovieObjectBuilder movie = new MovieObjectBuilder(title);
		dlm_WantToSee.addElement(movie);
		wantToSeeList.setModel(dlm_WantToSee);
	}
	
	private void actionDelete_WantToSee(){
		int index = wantToSeeList.getSelectedIndex();
		if(index >=0){
			wantToSeeTextPane.setText("");
			((DefaultListModel) wantToSeeList.getModel()).removeElementAt(index);
		}
	}
	
	
	
	
	// RECOMENDATIONS
	
	private void actionGetRecomendations(){
		if(HaveSeenList.isEmpty()){
			showError("You must have at least one rated movie in your have seen list");
		}
		else{	
			MovieObjectBuilder highestRated = FindHighestRatedMovie();
			Recommendations rec = new Recommendations(highestRated);
			DefaultListModel dlm = new DefaultListModel();
			MovieObjectBuilder[] recArray = rec.getRecommendationList();
			for(int i=0; i< recArray.length; i++){
				RecommendationList.add(recArray[i]);
				System.out.println(recArray[i].toString());
				System.out.println();
				dlm.addElement(recArray[i]);
			}
			recomendationsList.setModel(dlm);
		}
			
	}
	
	private void actionDelete_Recomendations(){
		int index = recomendationsList.getSelectedIndex();
		if(index >=0){
			recomendationsTextPane.setText("");
			((DefaultListModel) recomendationsList.getModel()).removeElementAt(index);
		}
	}
	
	
	// NEW RELEASES
	
	private void actionGetNewReleases(){
		NewReleases relist = new NewReleases();
		MovieObjectBuilder[] rearray = relist.getNewReleaseList();
		DefaultListModel dlm = new DefaultListModel();
		for(int i=0; i<rearray.length; i++){
			NewReleasesList.add(rearray[i]);
			System.out.println(rearray[i].toString());
			System.out.println();
			dlm.addElement(rearray[i]);
		}
		newReleasesList.setModel(dlm);
	}
	
	private void actionDelete_NewReleases(){
		int index = newReleasesList.getSelectedIndex();
		if(index >=0){
			newReleasesTextPane.setText("");
			((DefaultListModel) newReleasesList.getModel()).removeElementAt(index);
		}
	}
	
	
	
	
	private MovieObjectBuilder FindHighestRatedMovie(){
		ListIterator<MovieObjectBuilder> iterator = HaveSeenList.listIterator();
		MovieObjectBuilder highestrated = HaveSeenList.get(0);
		while(iterator.hasNext()){
			MovieObjectBuilder current = iterator.next();
			if(Integer.parseInt(current.getMyRating()) > Integer.parseInt(highestrated.getMyRating())){
				highestrated = current;
			}
		}
		
		return highestrated;
	}
	
	
	
	
	// Show dialog box with error message.
		private void showError(String errorMessage) {
			JOptionPane.showMessageDialog(this, errorMessage, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to your movie tracker");
		MainMenu();
		
	}//end main
	
	
	
	
	public static void MainMenu(){
		while(true){
			System.out.println();
			System.out.println("MAIN MENU");
			System.out.println("Option 1: view your movie lists");
			System.out.println("Option 2: Add movies to your lists");
			System.out.println("Option 3: Exit");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			if(selection.equals("1")){
				ListMenu();
			}
			if(selection.equals("2")){
				AddMovies();
			}
			if(selection.equals("3")){
				scan.close();
				System.exit(0);
			}
			else{
				System.out.println("Invalid input");
			}
		
		
		}
	}
	
	
	
	
	
	
	
	public static void ListMenu(){
		while(true){
			System.out.println();
			System.out.println("LIST MENU");
			System.out.println("Option 1: view the movies you have seen");
			System.out.println("Option 2: view the movies you want to see");
			System.out.println("Option 3: view new releases");
			System.out.println("Option 4: View your recommendations");
			System.out.println("Option 5: Go back to main menu");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			if(selection.equals("1")){
				if(HaveSeenList.isEmpty()){
					System.out.println("The list is empty");
					MainMenu();
				}
				else{	
					for(int i=0; i < HaveSeenList.size(); i++){
					MovieObjectBuilder movie = HaveSeenList.get(i);
					System.out.println(movie.toString());
					System.out.println();
					}	
					MainMenu();
				}
			}
			
			if(selection.equals("2")){
				if(WantToSeeList.isEmpty()){
					System.out.println("The list is empty");
					MainMenu();
				}
				else{	
					for(int i=0; i < WantToSeeList.size(); i++){
					MovieObjectBuilder movie = WantToSeeList.get(i);
					System.out.println(movie.toString());
					System.out.println();
					}	
					MainMenu();
				}
			}
			
			if(selection.equals("3")){
				NewReleases relist = new NewReleases();
				MovieObjectBuilder[] rearray = relist.getNewReleaseList();
				for(int i=0; i<rearray.length; i++){
					NewReleasesList.add(rearray[i]);
					System.out.println(rearray[i].toString());
					System.out.println();
				}
				MainMenu();
			}
		
			if(selection.equals("4")){
				if(HaveSeenList.isEmpty()){
					System.out.println("You must add movies to your HAVE SEEN list so we can make recommendations");
					MainMenu();
				}
				else{	
					//This should be a method [FindHighestRated()]
					MovieObjectBuilder highestRated = HaveSeenList.get(0);
					for(int i = 0; i <= HaveSeenList.size()-1; i++){
						MovieObjectBuilder currentMovie = HaveSeenList.get(i);
						if(Integer.parseInt(currentMovie.getMyRating()) > Integer.parseInt(highestRated.getMyRating())){
							highestRated = currentMovie;
						}
					}//End
					Recommendations rec = new Recommendations(highestRated);
					MovieObjectBuilder[] recArray = rec.getRecommendationList();
					for(int i=0; i< recArray.length; i++){
						RecommendationList.add(recArray[i]);
						System.out.println(recArray[i].toString());
						System.out.println();
					}
					MainMenu();
				}
			}

			if(selection.equals("5")){ //Goes back to main menu
				MainMenu();
			}
			else{ //returns control to while loop in this method 
				System.out.println("Invalid input");
			}
		
			
		}
	}
	
	
	
	
	
	public static void AddMovies(){
		while(true){
			String title;
			String year;
			System.out.println();
			System.out.println("Add Movies Menu");
			System.out.println("Option 1: Add to the moives you have seen");
			System.out.println("Option 2: Add to the movies you want to see");
			System.out.println("Option 3: Add moives to new releases");
			System.out.println("Option 4: Go back to main menu");
			
			Scanner scan = new Scanner(System.in);
			String selection = scan.next();
			
			Scanner sc = new Scanner(System.in).useDelimiter("\\n");  // USE THIS SCANNER OBJECT IN YOUR CODE BELOW
			
			if(selection.equals("1")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				MovieObjectBuilder movie = new MovieObjectBuilder(title);
				System.out.println("How would you rate this movie?  (1 to 5)");
				boolean tryAgain = true;
				while(tryAgain){
					String rating = scan.next();
					if(rating.equals("1")||rating.equals("2")||rating.equals("3")||rating.equals("4")||rating.equals("5")){
						movie.setMyRating(rating);
						tryAgain = false;
					}else{
						System.out.println("You must enter a number between 1 and 5");
					}
				}
				HaveSeenList.add(movie);
				MainMenu();
			}
			if(selection.equals("2")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				MovieObjectBuilder movie = new MovieObjectBuilder(title);
				WantToSeeList.add(movie);
				MainMenu();
			}
			if(selection.equals("3")){
				System.out.println("Enter a moive title");
				title = sc.nextLine();
				MovieObjectBuilder movie = new MovieObjectBuilder(title);
				NewReleasesList.add(movie);
				MainMenu();
			}
			if(selection.equals("4")){ //Goes back to main menu
				MainMenu();  
			}
			else{ //returns control to while loop in this method 
				System.out.println("Invalid input");
			}
		}
	}
	
	private MovieObjectBuilder FindHighestRatedMovie(){
		ListIterator<MovieObjectBuilder> iterator = HaveSeenList.listIterator();
		MovieObjectBuilder highestrated = HaveSeenList.get(0);
		while(iterator.hasNext()){
			MovieObjectBuilder current = iterator.next();
			if(Integer.parseInt(current.getMyRating()) > Integer.parseInt(highestrated.getMyRating())){
				highestrated = current;
			}
		}
		
		return highestrated;
	}
*/


}
	