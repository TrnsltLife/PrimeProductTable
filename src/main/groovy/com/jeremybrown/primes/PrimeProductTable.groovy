package com.jeremybrown.primes

import groovy.json.StringEscapeUtils

class PrimeProductTable
{
	static final int N_PRIMES_DEFAULT = 10
	static final int NO_PRIMES = -1
	static final Primes.Method METHOD_DEFAULT = Primes.Method.TABLE
	static final HELP_SWITCHES = ["?", "h", "help", "/?", "/h", "/help", "-?", "-h", "-help", "--help"].asImmutable()
	static final METHOD_MAP = ["table":Primes.Method.TABLE, "factor":Primes.Method.MODULUS, "sieve":Primes.Method.SIEVE_OF_ERATOSTHENES]

	public static void main(String[] args)
	{
		//Get the number of primes to print, or NO_PRIMES if there's an error or to print the usage statement.
		def command = [nPrimes:N_PRIMES_DEFAULT, method:METHOD_DEFAULT]
		parseArgs(args, command);
		
		//Print the usage statement
		if(command.nPrimes == NO_PRIMES)
		{
			printUsage()
			System.exit(0)
		}
		
		println("Printing ${command.nPrimes} prime number${command.nPrimes>1?'s':''} using the ${command.method} method.")
		List primes = Primes.findPrimes(command.nPrimes, command.method)
		if(primes.size() > 0 && primes[0] != -1)
		{
			println(createPrimeProductTable(primes))
		}
	}
	
	public static void parseArgs(String[] args, Map command)
	{
		//Parse and return the number of primes to display.
		//Return -1 (NO_PRIMES) to signal printing the usage statement and exiting.
		command.nPrimes = N_PRIMES_DEFAULT
		command.method = METHOD_DEFAULT
		
		//Were any args passed in on the command line?
		if(args.size() > 2)
		{
			System.err.println("Error: Too many parameters.")
			command.nPrimes = NO_PRIMES
			return
		}
		else if(args.size() >= 1)
		{
			//If the first one is an integer...
			if(args[0].isInteger())
			{
				int n = Integer.parseInt(args[0])
				//Make sure it's not an invalid number of primes
				if(n <= 0)
				{
					System.err.println("Error: <number-of-primes> must be >= 1")
					command.nPrimes = NO_PRIMES
					return
				}
				
				//Make sure there aren't any following arguments.
				if(args.size() > 1)
				{
					System.err.println("Error: Too many parameters.")
					command.nPrimes = NO_PRIMES
					return
				}
				
				//That's how many primes we want to display.
				command.nPrimes = n
				return
			}
			else if(METHOD_MAP.keySet().contains(args[0]))
			{
				command.method = METHOD_MAP[args[0]]
			}
			//If it's some variant of a command asking for help...
			else if(HELP_SWITCHES.contains(args[0]))
			{
				//...print the usage command and exit
				command.nPrimes = NO_PRIMES
				return
			}
			//If the first argument is something else
			else
			{
				System.err.println("Error: Invalid argument " + args[0])
				command.nPrimes = NO_PRIMES
				return
			}
		}
		//If there's a second argument it should be a number
		if(args.size() >= 2)
		{
			//If the argument is an integer...
			if(args[1].isInteger())
			{
				int n = Integer.parseInt(args[1])
				//Make sure it's not an invalid number of primes
				if(n <= 0)
				{
					System.err.println("Error: <number-of-primes> must be >= 1")
					command.nPrimes = NO_PRIMES
					return
				}
				//That's how many primes we want to display.
				command.nPrimes = n
				return
			}
		}
		//Use the default number of primes
		return
	}
	
	static void printUsage()
	{
		println("PrimeProductTable [<method>] [<number-of-primes>]")
		println("                              <number-of-primes>: default=10")
		println("                   <method>: table|factor|sieve : default=table")
	}
	
	static String createPrimeProductTable(List primes)
	{
		if(primes.size() == 0 || primes[0] == -1)
		{
			return ""
		}
		
		StringBuffer out = new StringBuffer()
		String EOL = System.lineSeparator()
		
		//Find the longest prime product, count its digits
		long max = primes[-1]
		max*=max
		String sMax = "" + max
		int maxDigits = sMax.length() + 1 //+1 for space between numbers
		
		//This will be the format for all the numbers
		String format = "%" + maxDigits + "d"
		
		//Print the header row, including list of the prime numbers to multiply
		int limit = primes.size()
		out.append(" " * maxDigits + "|")
		StringBuffer sb = new StringBuffer()
		for(int c=0; c<limit; c++)
		{
			sb.append(String.format(format, primes[c]))
		}
		String header = sb.toString()
		out.append(header+EOL)
		
		//Print the header row's underline
		out.append("-" * maxDigits)
		out.append("+")
		out.append("-" * header.length()+EOL)
		
		//Print the multiplication table by rows and columns, including labels of prime numbers to multiply
		for(int r=0; r<limit; r++)
		{
			//Print row label and | separator
			out.append(String.format("%" + maxDigits-1 + "d" + "|", primes[r]))
			
			//Print all multiplication for this row
			for(int c=0; c<limit; c++)
			{
				long value = primes[r] * primes[c]
				out.append(String.format(format, value))
			}
			out.append(EOL)
		}
		
		return out.toString()
	}
}
