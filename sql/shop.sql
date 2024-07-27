CREATE DATABASE IF NOT EXISTS sounshop;
USE sounshop;

CREATE TABLE `productInfo`(
    `productId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `productName` VARCHAR(255) NOT NULL,
    `category` VARCHAR(255) NOT NULL,
    `entryPrice` INT NOT NULL,
    `sellPrice` INT NOT NULL,
    `addDate` DATE NOT NULL,
    `isDelete` BOOLEAN NOT NULL,
    UNIQUE(`productName`)
);

CREATE TABLE `storage`(
    `productId` INT NOT NULL PRIMARY KEY,
    `quantity` INT NOT NULL DEFAULT 0,
    FOREIGN KEY(`productId`) REFERENCES `productInfo`(`productId`)
);

CREATE TABLE `salesInfo`(
    `tradingId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `productId` INT NOT NULL,
    `clientName` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `tradingQuantity` INT NOT NULL,
    `price` INT NOT NULL,
    `tradingDate` DATE NOT NULL,
    FOREIGN KEY(`productId`) REFERENCES `productInfo`(`productId`)
);

CREATE TABLE `entryProduct`(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `productId` INT NOT NULL,
    `quantity` INT NOT NULL,
    `price` INT NOT NULL,
    `entryDate` DATE NOT NULL,
    FOREIGN KEY(`productId`) REFERENCES `productInfo`(`productId`)
);

CREATE TABLE `admins`(
    `userId` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`userId`)
);

-- Thêm người dùng mặc định
INSERT INTO `admins` (`userId`, `password`) VALUES ('son', '1234abcd');

-- Trigger để tự động thêm sản phẩm vào kho khi sản phẩm mới được thêm vào productInfo
DELIMITER $$
CREATE TRIGGER after_product_insert
AFTER INSERT ON productInfo
FOR EACH ROW
BEGIN
    INSERT INTO storage (productId, quantity) VALUES (NEW.productId, 0);
END$$
DELIMITER ;

-- Trigger để cập nhật số lượng sản phẩm trong kho khi có nhập hàng
DELIMITER $$
CREATE TRIGGER after_entry_insert
AFTER INSERT ON entryProduct
FOR EACH ROW
BEGIN
    UPDATE storage SET quantity = quantity + NEW.quantity WHERE productId = NEW.productId;
END$$
DELIMITER ;
