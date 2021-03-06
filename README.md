# Project 3 - *Parsegram*

**Parsegram** is a photo sharing app similar to Instagram but using Parse as its backend.

Time spent: **5** hours spent in total

## User Stories

The following **required** functionality is completed:

- [x] User can view the last 20 posts submitted to "Instagram".
- [x] The user should switch between different tabs - viewing all posts (feed view), compose (capture photos form camera) and profile tabs (posts made) using fragments and a Bottom Navigation View. (2 points)
- [x] User can pull to refresh the last 20 posts submitted to "Instagram".

The following **optional** features are implemented:

- [ ] User sees app icon in home screen and styled bottom navigation view
- [x] Style the feed to look like the real Instagram feed.
    - To a degree
- [ ] User can load more posts once he or she reaches the bottom of the feed using infinite scrolling.
- [x] Show the username and creation time for each post.
- [ ] User can tap a post to view post details, including timestamp and caption.
- [ ] User Profiles
      - [ ] Allow the logged in user to add a profile photo
      - [x] Display the profile photo with each post
      - [ ] Tapping on a post's username or profile photo goes to that user's profile page and shows a grid view of the user's posts 
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [ ] User can like a post and see number of likes for each post in the post details screen.

The following **additional** features are implemented:

- [x] Custom Action bar items for each fragment to customize the interactions a user has while rendering different fragments

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/mrmikeyc/Parsegram/blob/master/ParsegramPt2_GeneralWalkthrough.gif' title='Video Walkthrough' width='360' alt='Video Walkthrough' />

GIF created with ScreenToGif.

## Notes

Describe any challenges encountered while building the app.

<hr>

## Part 1

Time spent: **14** hours spent in total

## User Stories

The following **required** functionality is completed:

- [x] User can sign up to create a new account using Parse authentication.
- [x] User can log in and log out of his or her account.
- [x] The current signed in user is persisted across app restarts.
- [x] User can take a photo, add a caption, and post it to "Instagram".

The following **optional** features are implemented:

- [ ] User sees app icon in home screen and styled bottom navigation view
- [ ] Style the feed to look like the real Instagram feed.
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse.

The following **additional** features are implemented:

- [x] Loading bar for sign up and login page while Parse is creating/authenticating
- [x] Floating action button to bring user to the create post section
- [ ] ** Need to implement swipe to refresh on recyclerview, or manually push newest created post to recycler view **

## Video Walkthrough

Here's a walkthrough of implemented user stories:

Signup functionality:

<img src='https://github.com/mrmikeyc/Parsegram/blob/master/ParsegramPt1_Signup.gif' title='Video Walkthrough' width='360' alt='Video Walkthrough' />

Login functionality:

<img src='https://github.com/mrmikeyc/Parsegram/blob/master/ParsegramPt1_Login.gif' title='Video Walkthrough' width='360' alt='Video Walkthrough' />

Create post functionality:

<img src='https://github.com/mrmikeyc/Parsegram/blob/master/ParsegramPt1_CreatePost.gif' title='Video Walkthrough' width='360' alt='Video Walkthrough' />

GIFs created with ScreenToGif

## Notes

- Attempted to implement features before watching the videos; Used what I remembered from previous videos to build out functionality (which is why I spent so many hours on this project)

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
