/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) 
    {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() 
    {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() 
    {
        //Try
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) 
    {
        if (size < maxSize) //Here we check if there any free place in the arrayy
        {
            tracks[size] = track;
            size++;
            return true;
        }
        return false;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() 
    {
        String result = "";
        for (int i = 0; i < size; i++) 
        {
            result += tracks[i].toString() + "\n";
        }
        return result;
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() 
     {
        if (size > 0) 
        {
            size--;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() 
    {
        int Total_Duration = 0;
        for (int i = 0; i < size; i++) 
        {
            Total_Duration += tracks[i].getDuration();
        }
        return Total_Duration;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) 
    {
        for (int i = 0; i < size; i++)
        {
            String temp = tracks[i].getTitle().toLowerCase(); // We convert the track in current location to lowercase for comparing to the give title
            if ( temp.equals(title.toLowerCase()))
            {
                return i; // Here we return the index if it is found
            } 
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if (i >= 0 && i <= size && size < maxSize)  //The input is correct and the list is not full
        {
            for (int j = size; j > i; j--) 
            {
                tracks[j] = tracks[j - 1]; //We move all the values one location foword in order the puth the new track
            }
            size++;
            tracks[i] = track;
            return true;
        }
        return false;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) 
    {
        if (i >= 0 && i < size) 
        {
            for (int j = i; j < size - 1; j++) 
            {
                tracks[j] = tracks[j + 1];
            }
            size--;
        }
        else
        {
            return ;
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) 
    {
        int location = indexOf(title);
        if (location != -1) 
        {
            remove(location);
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() 
    {
        if (size == 0)
        { // If the list is empty
            return;
        } 
        else
        {
            remove(0); // removes the first track    
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) 
     {
        int NewSize = size + other.size; //the size of the new list

        if (NewSize <= maxSize)
        { 
            for (int j = 0; j < other.size; j++)
            {
                add(other.tracks[j]);
                size = NewSize;
            }
        }
        else
        {
            return ;
        }
     }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) 
    {
        if (start < 0 || start >= size)
        {
        return -1;
        }
        int Minimal_Index = start; //Initializing the minimal index
        for (int i = start + 1; i < size; i++) 
        {
            if (tracks[i].getDuration() < tracks[Minimal_Index].getDuration()) //Looking for the lowest value from the given index
            {
                Minimal_Index = i;
            }
        }
        return Minimal_Index;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() 
    {
        for (int i = 0; i < size - 1; i++) 
        {
            int Minimal_index = minIndex(i); //looking fot the minimal value from i
            Track temp = tracks[i]; // we define temp track which represents the mimimal duration track from i
            tracks[i] = tracks[Minimal_index]; //we change the i location with the minimal duration track
            tracks[Minimal_index] = temp; //We change the tracks because we dont want to delete this track
        }
    }
}
