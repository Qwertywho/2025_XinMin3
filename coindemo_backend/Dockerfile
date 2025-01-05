FROM node:17-alpine3.15

# # Set the working directory
WORKDIR /app

# #copy files
COPY package.json package-lock.json ./

# #install node_modules
RUN npm install

# #copy the rest of application code
COPY . .

# # Build the application (if applicable)
RUN npm run build

#Expose port that React app runs on
EXPOSE 3000

CMD ["npm", "start"]