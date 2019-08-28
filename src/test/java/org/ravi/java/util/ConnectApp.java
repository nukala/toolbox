package org.ravi.java.util;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Optional;

public class ConnectApp {
    private static RecentIssues[] getIssues() {
        String issue = System.getProperty("issue");
        if (StringUtils.isEmpty(issue)) {
            return RecentIssues.values();
        }

        Optional<RecentIssues> issuesOptional = Arrays.stream(RecentIssues.values())
                .filter(ri ->
                        StringUtils.containsIgnoreCase(ri.name(), issue))
                .findFirst();

        if (issuesOptional.isPresent()) {
            //System.setProperty("java");
            return new RecentIssues[]{issuesOptional.get()};
        } else {
            throw new IllegalArgumentException("unknown issue [" + issue + "]");
        }
    }

    public static void main(String... args) {
        boolean verbose = BooleanUtils.toBoolean(System.getProperty("verbose", "false"));
        StringBuilder sb = new StringBuilder(4096);

        Arrays.stream(getIssues())
                .forEach(issue -> {
                    boolean success = true;
                    int numChars = 0;
                    try {
                        URL url = new URL(issue.getUrl());
                        URLConnection connection = url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            numChars += StringUtils.length(line);
                            if (verbose) {
                                System.out.printf("%s%n", line);
                            }
                        }
                    } catch (IOException e) {
                        success = false;
                        System.err.printf(">>>> %s(%s) %n", issue.name(), issue.getUrl());
                        e.printStackTrace();
                    }

                    String status = (success ? "PASS" : "FAILURE");
                    new Formatter(sb).format("%s:  %s(%s)\t(%d chars)%n", status, issue.name(), issue.url, numChars);
                });

        System.out.printf("%n%n%n=====%n%s-----%n", sb);
    }

    public enum RecentIssues {
        MmtFX("https://www.moresistemas.com:5005/aws_api_rates.aspx?wsdl"),
        CamHT("https://services.camtransfer.com"),
        LandbankPH("https://www.lbp-ieasypadala.com/ws/cxf/remittance?wsdl"),
        RpnbPH("https://remittanceservices1.pnb.com.ph/ars_ws/ars.asmx?WSDL"),
        BtsMany("https://secure.globalplatform.ws/gpcs/gprs/reportservice.asmx?wsdl"),
        PrabhuNP("https://www.prabhuusa.com/SendWsv2/txnservice.asmx"),
        AxisIN("https://testdirectremit.axisbank.co.in"),
        RcbcPH("https://rcbctelemoney.rcbc.com.ph/tripwebservice/rwservice.asmx?wsdl"),;

        private String url;

        RecentIssues(String url) {
            this.url = url;
        }

        public String getUrl() { return url; }
    }
}
