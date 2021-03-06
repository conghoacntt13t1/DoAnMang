import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by hoa on 12/03/2017.
 */
//public class ClientThuNghiem extends JFrame implements ActionListener{
public class ClientThuNghiem extends JFrame{
    //GUI
    JPanel pn,pn1,pn2,pn3;
    JLabel lbPcName, lbIP, lbTotal, lbUsed, lbCPU, lbMemory;
    JTextField txtPCName, txtIP, txtCPUUsed, txtCPUTotal, txtMemUsed, txtMemTotal;
    JButton btnGet;

    //Network
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    //get system information
//    Runtime runtime;
    OperatingSystemMXBean operatingSystemMXBean;
    MemoryMXBean memoryMXBean;

    public ClientThuNghiem (String title){
        super(title);
        AnhXa();
        getSystemInfo();
//        Connect();
    }

    private void getSystemInfo() {

    }

    private void Connect() {
        try {
            socket=new Socket("localhost",7000);
            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AnhXa() {

        pn=new JPanel(new GridLayout(3,1));
        pn1=new JPanel(new GridLayout(2,2));
        pn2=new JPanel(new GridLayout(3,3));
	pn3=new JPanel(new FlowLayout());

        lbCPU=new JLabel("CPU");
        lbIP=new JLabel("IP Address");
        lbMemory=new JLabel("Memory");
        lbPcName=new JLabel("Client PC Name:");
        lbTotal=new JLabel("Total");
        lbUsed=new JLabel("Used");

        txtCPUTotal=new JTextField();
//        txtCPUTotal.setEnabled(false);
        txtCPUUsed=new JTextField();
//        txtCPUUsed.setEnabled(false);
        txtIP=new JTextField();
//        txtIP.setEnabled(false);
        txtPCName=new JTextField();
//        txtPCName.setEnabled(false);
        txtMemTotal=new JTextField();
//        txtMemTotal.setEnabled(false);
        txtMemUsed=new JTextField();
//        txtMemUsed.setEnabled(false);

        btnGet=new JButton("Get start");
//        btnGet.addActionListener(this);

        pn1.add(lbPcName);
        pn1.add(txtPCName);
        pn1.add(lbIP);
        pn1.add(txtIP);
        pn.add(pn1);

        pn2.add(new JPanel());
        pn2.add(lbUsed);
        pn2.add(lbTotal);
        pn2.add(lbCPU);
        pn2.add(txtCPUUsed);
        pn2.add(txtCPUTotal);
        pn2.add(lbMemory);
        pn2.add(txtMemUsed);
        pn2.add(txtMemTotal);
        pn.add(pn2);

//        pn3.add(btnGet);
	    pn.add(pn3);

        add(pn);
        setVisible(true);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();


        txtPCName.setText(System.getProperty("user.name"));
        try {
            txtIP.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        int i=0;
        while(i<500){
            try {
                Method method=operatingSystemMXBean.getClass().getMethod("getTotalPhysicalMemorySize");
                method.setAccessible(true);
                Object value=method.invoke(operatingSystemMXBean);
                Long total = Long.parseLong(value+"")/(1024*1024);
                txtMemTotal.setText(total+"MB");
                method=operatingSystemMXBean.getClass().getMethod("getFreePhysicalMemorySize");
                method.setAccessible(true);
                value = method.invoke(operatingSystemMXBean);
                Long used = total - Long.parseLong(value+"")/(1024*1024);
                txtMemUsed.setText(used+"MB");
                Thread.sleep(500);
                i++;
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

//
//    @Override
//    public void actionPerformed(ActionEvent e) {
////        runtime=Runtime.getRuntime();
//        txtPCName.setText(System.getProperty("user.name"));
//        try {
//            txtIP.setText(InetAddress.getLocalHost().getHostAddress());
//        } catch (UnknownHostException e1) {
//            e1.printStackTrace();
//        }
//
//        int i=0;
//        while(i<500){
//            try {
//                Method method=operatingSystemMXBean.getClass().getMethod("getTotalPhysicalMemorySize");
//                method.setAccessible(true);
//                Object value=method.invoke(operatingSystemMXBean);
//                Long total = Long.parseLong(value+"")/(1024*1024);
//                txtMemTotal.setText(total+"MB");
//                method=operatingSystemMXBean.getClass().getMethod("getFreePhysicalMemorySize");
//                method.setAccessible(true);
//                value = method.invoke(operatingSystemMXBean);
//                Long used = total - Long.parseLong(value+"")/(1024*1024);
//                txtMemUsed.setText(used+"MB");
//                Thread.sleep(500);
//                i++;
//            }catch (Exception e1){
//                e1.printStackTrace();
//            }
//        }
//
//    }



    public static void main(String[] args) {
        ClientThuNghiem clientThuNghiem=new ClientThuNghiem("Client thu nghiem");
    }
}
