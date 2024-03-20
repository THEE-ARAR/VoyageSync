# Task Manager Overview

This project allows you to explore various technologies and aspects of full-stack development, including authentication, database integration, real-time updates, and deployment. Remember to refer to the documentation of each technology for detailed implementation guidance.

## NoSQL Database Design (Using Firebase Firestore):

**Collections:**
1. **Tasks:**
    - Attributes:
        - `taskId` (Unique identifier for each task)
        - `title` (Task title)
        - `description` (Task description)
        - `status` (Task status - e.g., "To Do," "In Progress," "Completed")
        - `createdAt` (Timestamp when the task was created)
        - `updatedAt` (Timestamp when the task was last updated)
        - `assignedUsers` (Array of user IDs assigned to the task)
        - `createdBy` (User ID of the creator)
        - `comments` (Array of comments on the task)
        - `dueDate` (Due date for the task)
        - `labels` (Array of labels/tags associated with the task)
        - `checklist` (Array of checklist items for the task)

2. **Users:**
    - Attributes:
        - `userId` (Unique identifier for each user)
        - `username` (User's username)
        - `email` (User's email address)
        - `roles` (Array of roles assigned to the user)


3. **SharedTasks:**
    - Attributes:
        - `sharedTaskId` (Unique identifier for each shared task)
        - `taskId` (Foreign key referencing the task associated with shared task)
        - `sharedWithUsers` (Array of user IDs with whom the task is shared)

4. **Checklists:**
    - Attributes:
        - `checklistId` (Unique identifier for each checklist)
        - `items` (Array of checklist items)
        - `taskId` (Foreign key referencing the task associated with the checklist)

5. **Messages:**
    - Attributes:
        - `messageId` (Unique identifier for each message)
        - `senderId` (User ID of the sender)
        - `receiverId` (User ID of the receiver)
        - `content` (Message content)
        - `timestamp` (Timestamp when the message was sent)

## API Classes and Endpoints (Using Spring Boot):

**Classes:**

1. **TaskController:**
    - **Endpoints:**
        - `GET /tasks` - Get all tasks.
        - `GET /tasks/{taskId}` - Get details of a specific task.
        - `POST /tasks` - Create a new task.
        - `PUT /tasks/{taskId}` - Update an existing task.
        - `DELETE /tasks/{taskId}` - Delete a task.
        - `GET /tasks/status/{status}` - Get tasks by status.

2. **UserController:**
    - **Endpoints:**
        - `GET /users` - Get all users.
        - `GET /users/{userId}` - Get details of a specific user.
        - `POST /users` - Create a new user.
        - `PUT /users/{userId}` - Update user details.
        - `DELETE /users/{userId}` - Delete a user (admin-only).

3. **SharedTaskController:**
    - **Endpoints:**
        - `POST /shared-tasks/{taskId}/share` - Share a task with a user.
        - `DELETE /shared-tasks/{sharedTaskId}` - Unshare a task with a user.

4. **ChecklistController:**
    - **Endpoints:**
        - `GET /checklists/{taskId}` - Get the checklist for a specific task.
        - `POST /checklists/{taskId}` - Create a new checklist for a task.
        - `PUT /checklists/{checklistId}` - Update an existing checklist.
        - `DELETE /checklists/{checklistId}` - Delete a checklist.

5. **MessageController:**
    - **Endpoints:**
        - `GET /messages` - Get all messages for the authenticated user.
        - `GET /messages/{messageId}` - Get details of a specific message.
        - `POST /messages` - Send a new message.
        - `DELETE /messages/{messageId}` - Delete a message.

6. **AuthenticationController:**
    - **Endpoints:**
        - `POST /login` - User login.
        - `POST /register` - User registration.
        - `POST /logout` - User logout.

7. **RoleController:**
    - **Endpoints:**
        - `GET /roles` - Get all available roles.
        - `POST /roles` - Create a new role (admin-only).
        - `PUT /roles/{roleId}` - Update an existing role (admin-only).
        - `DELETE /roles/{roleId}` - Delete a role (admin-only).

**Security:**
- Use Spring Security to secure the API endpoints.
- Implement JWT-based authentication. The token should include user roles for role-based access control.

**Integration with Firebase:**
- Use the Firebase Admin SDK in a service class for integrating with Firebase Firestore.
- Implement methods to perform CRUD operations on tasks, users, shared tasks, checklists, and messages.

## Frontend Pages (Using ReactJS):

1. **Login Page:**
    - Allows users to log in using Auth0.

2. **Registration Page:**
    - Allows users to register for a new account.

3. **Kanban Board:**
    - Displays all tasks categorized by status with drag-and-drop functionality.
    - Each task displays detailed information, including assigned users, due date, comments, labels, and checklists.
    - Real-time updates using Firebase Firestore.

4. **Task Details Page:**
    - Allows editing and adding comments, labels, checklist items, and sharing tasks with users.
    - Displays checklist items.

5. **User Profile Page:**
    - Shows user details and allows editing.

6. **Messages Page:**
    - Displays a list of messages with other users.
    - Allows sending new messages and deleting messages.

7. **Admin Dashboard (Optional):**
    - Accessible only by users with admin roles.
    - Allows managing users, roles, and tasks.

8. **Error Page:**
    - Displays error messages for invalid actions or unauthorized access.

### Additional Considerations:

- Implement proper error handling on the frontend and provide meaningful error messages.
- Use React Router for navigation between pages.
- Integrate with Auth0 for user authentication on the frontend.
- Apply responsive design principles to ensure a good user experience on various devices.
- Use a state management library (e.g., Redux) for managing application state.
- Implement unit tests for React components and integration tests for API endpoints.

This revised design removes the direct association of tasks from users, making tasks independent entities that users can be associated with through the `assignedUsers` attribute in each task. Adjustments can be made based on specific project requirements and additional features.