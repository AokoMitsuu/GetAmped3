	� G   r�       �VmoE��H����.wg'*7Hn���تT!�����w�����D�%--�"RUUU�Q@  ��忰�������$�Z$��X9�3���<;;���p�&�i����MlT��5'&��� ADc�ڔј��lc�Op��Y��q�x&h��484 d*'�&�8���.%.,fZ�����O��2ÁJ^cq�H�/��|�j-ۥ׀�a�4�=��U�}�b��	cd'�V�,�x�޳���cH��!�5����Y�.�����8�%=���x�H� _AM4K���RY� �:��z�hー �<V�$X��O]�5�s�:	@�N��Y��ɷv����߾sx#��c������]� �F���8�
2R�
(C��9v|�r�}���M�V��-
�2�Y����zeN026X�e�u���I�qm(�~xb��p�u�"m�7M����˽�%�F��H;�V?�;D��o�j ���3C��RP[5nv՚w;�q$hl��������к��4����i������~��<�����?�������O���3�u����V�Ǎ=� ��3�����9
z*�!��ʁ��tZ�k/1�F�jICo_�Y�X--��ᒸ��<��	N��q	�:�+ ���ķ�AJ$i�S������&ki��M��*Ӵ�g� ���[�O�?�j��d���<~8�Z�������_|	`��{p�|�ӻ|�c�~�ӛ|�O?k��"�7-%o��G6D�FM��+Gl];&v�����"j�e]`s���e��WGҴ�m�i��ov��븵�&K�Ϗ��tR�uuX0̉�eNM�zY2�IF��zLXM4��l6c�ӆix%�qM�Z���2X�L��Qٲ��Œ�8�����!��Yy�P��>�fS
��\���|m�8����Z����[WzC�ǔ�^$V�{H#�eI'�����lb�Y�ia�=7�@��\�^_]�-]�K�
���� �{���'u�'vx��3Mqs���ͤN/R�x�r���;���k�KՕ�������y=�0��Εfч�Y��u������6��n^��^T@B�?�2�~{�/��/���wS&��S ��
   f  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
 f x y isServer ()Z
 f { | getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f ~  '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 	playSound
 d � 3 getArgv ()Ljava/lang/Object;
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � � min
 � � �B4   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            �X� �D /accs/psyco/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� D
�n                        �  