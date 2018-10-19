package w3;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseballElimination
{
	private static class Team
	{
		String name;
		int position;
		int wins;
		int losses;
		int remaining;
		int[] gamesVersus;
	}

	private final Map<String, Team> teams;

	private final String[] teamByPosition;

	// create a baseball division from given filename in format specified below
	public BaseballElimination(String filename)
	{
		In in = new In(filename);
		int competitors = Integer.parseInt(in.readLine());
		teams = new HashMap<>(competitors);
		teamByPosition = new String[competitors];

		for (int i = 0; i < competitors; i++)
		{
			String[] teamDefinition = in.readLine().split(" ");
			Team team = new Team();
			team.position = i;
			team.name = teamDefinition[0];
			team.wins = Integer.parseInt(teamDefinition[1]);
			team.losses = Integer.parseInt(teamDefinition[2]);
			team.remaining = Integer.parseInt(teamDefinition[3]);

			for (int j = 0; j < competitors; j++)
			{
				team.gamesVersus[j] = Integer.parseInt(teamDefinition[j + 4]);
			}

			teamByPosition[i] = team.name;
			teams.put(team.name, team);
		}
	}

	// number of teams
	public int numberOfTeams()
	{
		return teamByPosition.length;
	}

	// all teams
	public Iterable<String> teams()
	{
		return Arrays.asList(teamByPosition);
	}

	// number of wins for given team
	public int wins(String team)
	{
		Team t = teams.get(team);
		checkTeam(t);
		return t.wins;
	}

	private void checkTeam(Team... t)
	{
		for (Team team : t)
		{
			if (team == null)
			{
				throw new IllegalArgumentException();
			}
		}
	}

	// number of losses for given team
	public int losses(String team)
	{
		Team t = teams.get(team);
		checkTeam(t);
		return t.losses;
	}

	// number of remaining games for given team
	public int remaining(String team)
	{
		Team t = teams.get(team);
		checkTeam(t);
		return t.remaining;
	}

	// number of remaining games between team1 and team2
	public int against(String team1, String team2)
	{
		Team t1 = teams.get(team1);
		Team t2 = teams.get(team2);
		checkTeam(t1, t2);

		return t1.gamesVersus[t2.position];
	}

	// is given team eliminated?
	public boolean isEliminated(String team)
	{
		Team t = teams.get(team);
		checkTeam(t);

		List<FlowEdge> gameEdges = new ArrayList<>();
		int vertexIndex = 1;

		for (String teamName : teamByPosition)
		{
			if (teamName.equals(team)) continue;
			Team competitor = teams.get(teamName);
			for (int i = competitor.position; i < competitor.gamesVersus.length; i++)
			{
				int games = competitor.gamesVersus[i];
				if (games == 0)
				{
					continue;
				}
				gameEdges.add(new FlowEdge(0, vertexIndex++, games));
			}
		}

		for (String name : teams.keySet())
		{
			if (team.equals(name))
			{
				continue;
			}

		}

		int v = 2 + gameEdges.size() + numberOfTeams() - 1;
		FlowNetwork flowNetwork = new FlowNetwork(v);
		for (FlowEdge gameEdge : gameEdges)
		{
			flowNetwork.addEdge(gameEdge);
		}


		FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0, v);
		return false;
	}

	// subset R of teams that eliminates given team; null if not eliminated
	public Iterable<String> certificateOfElimination(String team)
	{
		Team t = teams.get(team);
		checkTeam(t);

		return null;
	}

	public static void main(String[] args)
	{
		BaseballElimination division = new BaseballElimination(args[0]);
		for (String team : division.teams())
		{
			if (division.isEliminated(team))
			{
				StdOut.print(team + " is eliminated by the subset R = { ");
				for (String t : division.certificateOfElimination(team))
				{
					StdOut.print(t + " ");
				}
				StdOut.println("}");
			}
			else
			{
				StdOut.println(team + " is not eliminated");
			}
		}
	}
}


