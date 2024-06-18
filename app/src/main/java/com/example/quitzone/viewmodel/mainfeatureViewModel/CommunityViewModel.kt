package com.example.quitzone.ui.mainfeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quitzone.R

data class CommunityItem(val icon: Int, val title: String, val description: String, val category: String)
data class CommunityCategory(val title: String)

class CommunityViewModel : ViewModel() {

    private val _communityItems = MutableLiveData<List<CommunityItem>>(
        listOf(
            CommunityItem(R.drawable.ic_badminton, "Badminton", "Recreational sportspersons", "Physically"),
            CommunityItem(R.drawable.ic_fishing, "Fishing", "Relaxation seekers", "Relaxed"),
            CommunityItem(R.drawable.ic_running, "Running", "Fitness enthusiasts", "Physically"),
            CommunityItem(R.drawable.ic_swimming, "Swimming", "Exercise lovers", "Physically"),
            CommunityItem(R.drawable.ic_cycling, "Cycling", "Outdoor fitness aficionados", "Physically"),
            CommunityItem(R.drawable.ic_dumbell, "Fitness", "Gym goers and weightlifters", "Physically"),
            CommunityItem(R.drawable.ic_gardening, "Gardening", "Creative gardeners", "Creatively"),
            CommunityItem(R.drawable.ic_cooking, "Cooking", "Culinary creatives", "Creatively"),
            CommunityItem(R.drawable.ic_writing, "Writing", "Passionate writers", "Creatively"),
            CommunityItem(R.drawable.ic_boardgame, "Board Games", "Social gamers", "Socially"),
            CommunityItem(R.drawable.ic_reading, "Reading Books", "Avid readers", "Relaxed"),
            CommunityItem(R.drawable.ic_yoga, "Yoga/Meditation", "Mindfulness practitioners", "Relaxed"),
            CommunityItem(R.drawable.ic_travelling, "Traveling", "Adventurous travelers", "Relaxed"),
            CommunityItem(R.drawable.ic_videgames, "Video Gaming", "Leisure gamers", "Relaxed"),
            CommunityItem(R.drawable.ic_volunteer, "Volunteering", "Community contributors", "Socially")
        )
    )
    val communityItems: LiveData<List<CommunityItem>> = _communityItems

    private val _communityCategories = MutableLiveData<List<CommunityCategory>>(
        listOf(
            CommunityCategory("All"),
            CommunityCategory("Physically"),
            CommunityCategory("Creatively"),
            CommunityCategory("Relaxed"),
            CommunityCategory("Socially")
        )
    )
    val communityCategories: LiveData<List<CommunityCategory>> = _communityCategories

    private val _selectedItem = MutableLiveData("All")
    val selectedItem: LiveData<String> = _selectedItem

    val filteredItems: MediatorLiveData<List<CommunityItem>> = MediatorLiveData<List<CommunityItem>>().apply {
        addSource(_selectedItem) { selectedItem ->
            value = if (selectedItem == "All") {
                _communityItems.value
            } else {
                _communityItems.value?.filter { it.category == selectedItem }
            }
        }
        addSource(_communityItems) { communityItems ->
            value = if (_selectedItem.value == "All") {
                communityItems
            } else {
                communityItems.filter { it.category == _selectedItem.value }
            }
        }
    }

    fun selectCategory(category: String) {
        _selectedItem.value = category
    }
}
