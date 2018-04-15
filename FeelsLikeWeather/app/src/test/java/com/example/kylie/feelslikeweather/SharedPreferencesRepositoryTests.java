package com.example.kylie.feelslikeweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.kylie.feelslikeweather.models.SharedPreferencesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Kylie on 2/25/2017.
 */

public class SharedPreferencesRepositoryTests {
    @Mock
    Context context;

    @Mock
    SharedPreferences preferences;

    @Mock
    PreferenceManager preferenceManager;


    @Before
    public void before() throws Exception{
        MockitoAnnotations.initMocks(this);
        Mockito.when(context.getSharedPreferences(anyString(),anyInt())).thenReturn(preferences);
    }


    @Test
    public void testLoadTwoFromPreferences()throws Exception{
        Mockito.when(preferences.getString("locations","")).thenReturn("12.3123421,345.123234|34.234543,123.343542");
        SharedPreferencesRepository repository = new SharedPreferencesRepository(context);
        ArrayList list = repository.getSavedLocations();
        assertEquals(2,list.size());
    }
    @Test
    public void testLoadFromEmptyPreferences()throws Exception{
        Mockito.when(preferences.getString("locations","")).thenReturn("");
        SharedPreferencesRepository repository = new SharedPreferencesRepository(context);
        ArrayList list = repository.getSavedLocations();
        assertEquals(null,list);
    }
    @Test
    public void testLoadOneLocationFromPreferences()throws Exception{
        Mockito.when(preferences.getString("locations","")).thenReturn("12.3123421,345.123234");
        SharedPreferencesRepository repository = new SharedPreferencesRepository(context);
        ArrayList list = repository.getSavedLocations();
        assertEquals(1,list.size());
    }
}
