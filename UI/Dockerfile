FROM node:14.15.4-alpine as build
WORKDIR /usr/local/app
COPY ./ /usr/local/app/
RUN npm install
RUN npm link @angular/cli
RUN ng build --prod

FROM nginx:1.17-alpine
RUN rm -rf /usr/share/nginx/html/*
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/local/app/dist/UI /usr/share/nginx/html
EXPOSE 80
