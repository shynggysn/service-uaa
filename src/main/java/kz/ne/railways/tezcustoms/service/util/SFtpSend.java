package kz.ne.railways.tezcustoms.service.util;

import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Vector;


@Slf4j
@Component
public class SFtpSend {

    // public String host="10.96.3.73"; //test
    public String host = "192.168.1.37"; // prod

    public boolean send(InputStream is, String path, String invoiceUn)
                    throws JSchException, SftpException, FileNotFoundException {
        boolean result = false;
        try {
            JSch jsch = new JSch();
            Session session = null;

            session = jsch.getSession("root", host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("Z7zBkA7ZQnRZdagDYTT2");
            session.connect();
            // File file = new File(path);
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            String invoiceIdPath = "";
            if (NumberUtils.isNumber(invoiceUn)) {
                Integer invoiceId = (Integer.parseInt(invoiceUn) / 10000) * 10000;
                log.debug("divided invoiceId:", invoiceId);
                invoiceIdPath = invoiceId.toString();
            }

            try {
                sftpChannel.mkdir("/dkrdata/files/EPDDOCFILES/" + invoiceIdPath);
            }catch (SftpException ex) {
                log.debug("in SFtp send", ex.getMessage());
            }

            try{
                sftpChannel.mkdir("/dkrdata/files/EPDDOCFILES/" + invoiceIdPath + "/" + invoiceUn);
            } catch (SftpException ex) {
                log.debug("in SFtp send", ex.getMessage());
            }

            sftpChannel.cd("/dkrdata/files/EPDDOCFILES/" + invoiceIdPath + "/" + invoiceUn);
            sftpChannel.put(is, path);
            sftpChannel.exit();
            session.disconnect();
            result = true;
        } catch(Exception ex){
            log.debug("in SFtp send", ex.getMessage());
        }

        return result;
    }

    public void get(String invoiceId, String uuid, java.io.OutputStream out)
                    throws JSchException, SftpException, FileNotFoundException {
        JSch jsch = new JSch();
        Session session = null;

        session = jsch.getSession("root", host, 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("Ephesus");
        session.connect();
        // File file = new File(path);
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        // sftpChannel.cd("/dkrdata/EPDDOCFILES/"+invoiceUn);
        System.out.println("path: " + "/dkrdata/files/EPDDOCFILES/" + invoiceId + "/" + uuid);
        String invoiceIdPath = "";
        if (NumberUtils.isNumber(invoiceId)) {
            Integer invoiceIdPathInt = (Integer.parseInt(invoiceId) / 10000) * 10000;
            invoiceIdPath = invoiceIdPathInt.toString();
        }
        String newPath = "/dkrdata/files/EPDDOCFILES/" + invoiceIdPath + "/" + invoiceId + "/" + uuid;
        String oldPath = "/dkrdata/files/EPDDOCFILES/" + invoiceId + "/" + uuid;
        Vector<LsEntry> lsEntryVector = new Vector<LsEntry>();
        try {
            lsEntryVector = sftpChannel.ls("/dkrdata/files/EPDDOCFILES/" + invoiceIdPath + "/" + invoiceId);
        } catch (SftpException ex) {
            // TODO: handle exception
        }
        Boolean foundInNewPath = false;
        for (LsEntry lsEntry : lsEntryVector) {
            if (uuid.equals(lsEntry.getFilename())) {
                foundInNewPath = true;
                break;
            }
        }
        if (foundInNewPath) {
            sftpChannel.get(newPath, out);
        } else {
            sftpChannel.get(oldPath, out);
        }
        sftpChannel.exit();
        session.disconnect();

        // return is;
    }

}
