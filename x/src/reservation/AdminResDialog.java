package reservation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminResDialog extends JDialog implements ActionListener, ItemListener{
	
	JPanel pPan, rPan; //last panel
	JPanel pcPan, rcPan;
	JPanel plPan, pdPan, rlPan, rdPan;
	JPanel pr1, rr1, r2, r3, r4; //radiobutton panel
	JLabel pld, plt, lk, lv, ls, lp, lo, rld, rlt;
	JTextField ptd, rtd;
	JComboBox vou, per;
	JRadioButton ppri, proom, rpri, rroom, kan, open, free, fix, yv, nv;
	ButtonGroup ptype = new ButtonGroup();
	ButtonGroup rtype = new ButtonGroup();
	ButtonGroup	bkan = new ButtonGroup();
	ButtonGroup seat = new ButtonGroup();
	ButtonGroup vim = new ButtonGroup();
	JButton pupdate, rupdate;
	
	ResDAO dao;
	AdminRes rtt ;
	
	String id;
	int row;
	
	public AdminResDialog(AdminRes rt) {
		setModal(true);
		rtt = rt;
		pld = new JLabel("이용일   ");				plt = new JLabel("개인/룸 ");				rld = new JLabel("이용일   ");				rlt = new JLabel("개인/룸 ");			
		lk = new JLabel("칸막이   ");				lv = new JLabel("이용권   ");		
		ls = new JLabel("좌석종류 ");				lp = new JLabel("인원      ");				lo = new JLabel("옵션(빔) ");				
		
		ptd = new JTextField(10);				rtd = new JTextField(10);
		
		String[] svou = {"------------------    ", "1일", "1주", "2주", "한달" };
		String[] sper = {"------------------    ", "4인", "6인", "8인" };
		
		vou = new JComboBox(svou);				per = new JComboBox(sper);
		vou.addItemListener(this);
		
		pr1 = new JPanel(new GridLayout(1,2));		rr1 = new JPanel(new GridLayout(1,2));		
		r2 = new JPanel(new GridLayout(1,2));		r3 = new JPanel(new GridLayout(1,2));		r4 = new JPanel(new GridLayout(1,2));
		
		ppri = new JRadioButton("개인", true);		proom = new JRadioButton("룸", false);	ptype.add(ppri);		ptype.add(proom);		pr1.add(ppri);		pr1.add(proom);
		ppri.addItemListener(this);					proom.addItemListener(this);
		rpri = new JRadioButton("개인", false);		rroom = new JRadioButton("룸", true);	rtype.add(rpri);		rtype.add(rroom);		rr1.add(rpri);		rr1.add(rroom);
		rpri.addItemListener(this);					rroom.addItemListener(this);
		kan = new JRadioButton("유", true);				open = new JRadioButton("무", false);			bkan.add(kan);			bkan.add(open);			r2.add(kan);		r2.add(open);
		free = new JRadioButton("자유석", true);			fix = new JRadioButton("고정석", false);			seat.add(free);			seat.add(fix);			r3.add(free);		r3.add(fix);
		yv = new JRadioButton("포함", false);				nv = new JRadioButton("미포함", true);			vim.add(yv);			vim.add(nv);			r4.add(yv);			r4.add(nv);
		
		pupdate = new JButton("수정");	pupdate.addActionListener(this);	
		rupdate = new JButton("수정");	rupdate.addActionListener(this);

		pPan = new JPanel(new BorderLayout());
		pcPan = new JPanel(new BorderLayout());
		plPan = new JPanel(new GridLayout(5, 1));
		pdPan = new JPanel(new GridLayout(5, 1));
		
		plPan.add(pld);			plPan.add(plt);			plPan.add(lk);			plPan.add(lv);			plPan.add(ls);
		pdPan.add(ptd);			pdPan.add(pr1);			pdPan.add(r2);			pdPan.add(vou);			pdPan.add(r3);	
		pcPan.add(plPan, "West");			pcPan.add(pdPan, "Center");
		pPan.add(pcPan, "Center");			pPan.add(pupdate, "South");
		
		rPan = new JPanel(new BorderLayout());
		rcPan = new JPanel(new BorderLayout());
		rlPan = new JPanel(new GridLayout(4, 1));
		rdPan = new JPanel(new GridLayout(4, 1));
		
		rlPan.add(rld);			rlPan.add(rlt);			rlPan.add(lp);			rlPan.add(lo);			
		rdPan.add(rtd);			rdPan.add(rr1);			rdPan.add(per);			rdPan.add(r4);	
		rcPan.add(rlPan, "West");			rcPan.add(rdPan, "Center");
		rPan.add(rcPan, "Center");			rPan.add(rupdate, "South");
		
		add(pPan);	add(rPan);	
		
		setResizable(false);
		setSize(300, 350);
		checkPan(rt);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	

	
	public void checkPan(AdminRes rt) {
		System.out.println("checkPan");
		row = rt.table.getSelectedRow();
		String st = rt.table.getValueAt(row, 4).toString().substring(0,1); 
		if(st.equals("1")) {
			priCheck(rt, row);
		} else {
			roomCheck(rt, row);
		}
	}
	
	public void priCheck(AdminRes rt, int row) {
		System.out.println("priCheck");
		String sd = rt.table.getValueAt(row, 3).toString(); //예약일
		String sk = rt.table.getValueAt(row, 0).toString().substring(0,1); //p(오픈) or c(칸막이)
		
		String sv = rt.table.getValueAt(row, 0).toString();
		int sv1 = sv.length();
		String sv2 = sv.substring(sv1-2, sv1); //기간
		
		String ss = rt.table.getValueAt(row, 5).toString(); //지정 or 자유
		
		ptd.setText(sd);
		
		if(sk.equals("p")) { //오픈형
			open.setSelected(true);
		} else {
			kan.setSelected(true);
		}
		
		if(sv2.contentEquals("1d")) {
			vou.setSelectedIndex(1);
		} else if(sv2.contentEquals("1w")) {
			vou.setSelectedIndex(2);
		} else if(sv2.contentEquals("2w")) {
			vou.setSelectedIndex(3);
		} else {
			vou.setSelectedIndex(4);
		}

		if(ss.equals("자유석")) {
			free.setSelected(true);
		} else {
			fix.setSelected(true);
		}
		
		rPan.setVisible(false);
		add(pPan);
		pPan.setVisible(true);
		setSize(300,350);
//		setVisible(true);
	}
	
	public void roomCheck(AdminRes rt, int row) {
		System.out.println("roomCheck");
		String sd = rt.table.getValueAt(row, 3).toString(); //예약일
		
		String sp = rt.table.getValueAt(row, 0).toString(); 
		int sp1 = sp.length();
		String sp2 = sp.substring(sp1-2, sp1-1); //인원
		
		String sv = rt.table.getValueAt(row, 6).toString(); //빔
		

		rtd.setText(sd);
		
		if(sp2.equals("4")) {
			per.setSelectedIndex(1);
		} else if (sp2.equals("6")) {
			per.setSelectedIndex(2);
		} else {
			per.setSelectedIndex(3);
		}
		
		if(sv.equals("미포함")) {
			nv.setSelected(true);
			yv.setSelected(false);
		} else {
			yv.setSelected(true);
			nv.setSelected(false);
		}
		pPan.setVisible(false);
		add(rPan);
		rPan.setVisible(true);
		setSize(300,350);
//		setVisible(true);
	}
	
	
	
	public void priPan(String d) { //1인실 d:날짜
		System.out.println("priPan");
		remove(rPan);
		validate();
		repaint();
		add(pPan);
		ppri.setSelected(true);
		proom.setSelected(false);
		ptd.setText(d);

		rPan.setVisible(false);
		pPan.setVisible(true);
		setSize(300,350);
//		setVisible(true);
		System.out.println("priPanEnd");
	}
	
	public void roomPan(String d) {
		System.out.println("roomPan");
		remove(pPan);
		validate();
		repaint();
		add(rPan);
		rpri.setSelected(false);
		rroom.setSelected(true);
		rtd.setText(d);
		
		pPan.setVisible(false);
		rPan.setVisible(true);
		setSize(300,350);
//		setVisible(true);
		System.out.println("roomPanEnd");
	}
	
	public String getCode() {
		String code = "";
		if(pPan.isVisible()) {
			System.out.println("pVisible");
			if(ppri.isSelected()) {//개인
				if(kan.isSelected())//칸막이
					code += "c";
				else if(open.isSelected())
					code += "p";
				
				if(free.isSelected())
					code += "Fr";
				else if(fix.isSelected())
					code += "Fi";
				
				if(vou.getSelectedIndex() == 1)
					code += "1d";
				else if(vou.getSelectedIndex() == 2)
					code += "1w";
				else if(vou.getSelectedIndex() == 3)
					code += "2w";
				else if(vou.getSelectedIndex() == 4)
					code += "1m";
				else {
					messageBox(this, "이용권을 선택하세요.");
				}				
			}
		} else if(rPan.isVisible()) {
			
			System.out.println("rVisible");
			if(rroom.isSelected()) {//단체
				code += "Sem";
					
				if(per.getSelectedIndex() == 1)
					code += "4";
				else if(per.getSelectedIndex() == 2)
					code += "6";
				else if(per.getSelectedIndex() == 3)
					code += "8";
				else
					messageBox(this, "인원수를 선택하세요.");
						
				if(yv.isSelected()) {
					code += "V";
				} else if(nv.isSelected()){
					code += "N";
				}
			} 
		} 
		return code;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dao = new ResDAO();
		id = (String) rtt.model.getValueAt(row, 1);
		System.out.println(row); 
		System.out.println(id);
		if(e.getSource() == pupdate) {
			System.out.println("pupdate");
			int ud = dao.adminpupdate(this, id);
			if (ud > 0) {
				messageBox(this, "수정 완료");
				dispose();
				dao.adminshowProcess(rtt.model);
			} else {
				messageBox(this, "수정 fail");
			}
	
		} else if (e.getSource() == rupdate) {
			System.out.println("rupdate");
			int ud = dao.adminrupdate(this, id);
			if (ud > 0) {
				messageBox(this, "수정 완료");
				dispose();
				dao.adminshowProcess(rtt.model);
			}  else {
				messageBox(this, "수정 fail");
			}
	
		} 
	}	
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getItem() == proom) {
			String d = ptd.getText();
			roomPan(d);
			return;
		} else if(e.getItem() == rpri) {
			String d = rtd.getText();
			priPan(d);	
			return;
		} 
		int item =  vou.getSelectedIndex();
		if (item == 1) {
			free.setSelected(true);
			fix.setEnabled(false);
		} else {
			free.setSelected(true);
			fix.setEnabled(true);
		}
	}
}
