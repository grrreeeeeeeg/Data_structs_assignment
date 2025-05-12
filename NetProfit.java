import java.io.*;

public class NetProfit {
    public static void main(String[] args) {
        String fpath = args[0]; //Declare text file path as main method argument
        try {
            //Initialize File Reader objects
            FileReader fr = new FileReader(fpath);
            BufferedReader br = new BufferedReader(fr);
            //Initialize Stack of transactions
            DoubleQueueImpl<Double> pricequeue = new DoubleQueueImpl<>();
            DoubleQueueImpl<Double> stockqueue = new DoubleQueueImpl<>();
            int total_stocks = 0; //Total stocks bought
            double netprofit = 0;
            //Read and process each line (transaction) as string vector
            while (br.ready()) {
                String line = br.readLine();
                String[] transaction = line.split(" ");
                String action = transaction[0]; //buy or sell action
                double stocks = Double.parseDouble(transaction[1]); //number of stocks
                double price = Double.parseDouble(transaction[3]); //price of each stock
                //If buy action, put price and stocks to queues
                if (action.equals("buy")) {
                    total_stocks += stocks;
                    pricequeue.put(price);
                    stockqueue.put(stocks);
                }
                //Sell action
                else {
                    //Not enough stocks to sell
                    if (stocks > total_stocks) {
                        System.out.println("Inaccessible amount of stocks!");
                        break;
                    }
                    //Enough stocks to sell
                    else {
                        total_stocks -= stocks;
                        double stocks_left = stocks;   //Number of stocks left to be sold
                        double new_price = price;   //Price when selling stocks
                        while (!pricequeue.isEmpty() && !stockqueue.isEmpty() && stocks_left > 0) {
                            double buyPrice = pricequeue.get(); //Price of previous transaction
                            double buyStocks = stockqueue.get(); //Number of stocks of previous transaction
                            //Calculate sum of net profits of previous transactions
                            if (stocks_left >= buyStocks) {
                                netprofit += buyStocks*(new_price - buyPrice); //if whole purchase is taken into consideration
                                stocks_left -= buyStocks;
                            }
                            else {
                                netprofit += stocks_left*(new_price - buyPrice); //if partial purchase is left out of the sum
                                stockqueue.put(buyStocks - stocks_left);
                                pricequeue.put(buyPrice);
                                stocks_left = 0;
                            }
                        }
                        System.out.println("Net Profit: " + netprofit);
                    }

                }
            }
            br.close();
        }
        catch (FileNotFoundException a) {
            System.out.println("Text file not found.");
        }
        catch (IOException b) {
            System.out.println("Text file not found.");
        }

    }
}
