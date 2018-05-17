package com.jeremybrown.primes

class PrimeProductTable
{
	static final int N_PRIMES_DEFAULT = 10
	static final int NO_PRIMES = -1
	static final HELP_SWITCHES = ["?", "h", "help", "/?", "/h", "/help", "-?", "-h", "-help", "--help"].asImmutable()

	public static void main(String[] args)
	{
		//Get the number of primes to print, or NO_PRIMES if there's an error or to print the usage statement.
		int n = parseArgs(args);
		
		//Print the usage statement
		if(n == NO_PRIMES)
		{
			printUsage()
			System.exit(0)
		}
		
		println("Print $n primes:")
		
		
	}
	
	public static int parseArgs(String[] args)
	{
		//Parse and return the number of primes to display.
		//Return -1 to signal printing the usage statement and exiting.
		
		//Were any args passed in on the command line?
		if(args.size() > 0)
		{
			//If the first one is an integer...
			if(args[0].isInteger())
			{
				int n = Integer.parseInt(args[0])
				//Make sure it's not an invalid number of primes
				if(n <= 0)
				{
					System.err.println("Error: <number-of-primes> must be >= 1")
					return NO_PRIMES
				}
				//Otherwise return it. That's how many primes we want to display.
				return n
			}
			//If it's some variant of a command asking for help...
			else if(HELP_SWITCHES.contains(args[0]))
			{
				//...print the usage command and exit
				return NO_PRIMES
			}
			//If the first argument is something else
			else
			{
				System.err.println("Error: Invalid argument " + args[0])
				return NO_PRIMES
			}
		}
		//Use the default number of primes
		return N_PRIMES_DEFAULT
	}
	
	static void printUsage()
	{
		println("PrimeProductTable <number-of-primes>")
		println("                  <number-of-primes>: default=10")
	}
}
